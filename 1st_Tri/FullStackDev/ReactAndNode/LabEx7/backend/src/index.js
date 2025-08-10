import http from "http";
import { readFile } from "fs/promises";
import { fileURLToPath, URL } from "url";
import { dirname, join } from "path";

const __filename = fileURLToPath(import.meta.url);
const __dirname = dirname(__filename);

const PORT = 3000;

http.createServer(async (req, res) => {
    // ✅ CORS Headers
    res.setHeader("Access-Control-Allow-Origin", "*");
    res.setHeader("Access-Control-Allow-Methods", "GET, OPTIONS");
    res.setHeader("Access-Control-Allow-Headers", "Content-Type");

    // ✅ Handle preflight request
    if (req.method === "OPTIONS") {
        res.writeHead(204);
        res.end();
        return;
    }

    // ✅ Routing
    const parsedUrl = new URL(req.url, `http://${req.headers.host}`);
    const pathname = parsedUrl.pathname;
    const searchParams = parsedUrl.searchParams;

    try {
        const jsonPath = join(__dirname, "./api/data.json");
        const jsonData = await readFile(jsonPath, "utf-8");
        const { restaurants } = JSON.parse(jsonData);

        if (pathname === "/api") {
            res.writeHead(200, { "Content-Type": "application/json" });
            res.end(JSON.stringify(restaurants));

        } else if (pathname === "/api/items") {
            // Return only item names
            const items = restaurants.map(r => r.item);
            res.writeHead(200, { "Content-Type": "application/json" });
            res.end(JSON.stringify(items));

        } else if (pathname === "/api/filter") {
            // Filter using query params
            let filtered = restaurants;

            if (searchParams.has("item")) {
                filtered = filtered.filter(r =>
                    r.item.toLowerCase().includes(searchParams.get("item").toLowerCase())
                );
            }
            if (searchParams.has("price")) {
                filtered = filtered.filter(r => r.price <= parseInt(searchParams.get("price")));
            }
            if (searchParams.has("category")) {
                filtered = filtered.filter(r =>
                    r.category.toLowerCase() === searchParams.get("category").toLowerCase()
                );
            }

            res.writeHead(200, { "Content-Type": "application/json" });
            res.end(JSON.stringify(filtered));

        } else {
            res.writeHead(404, { "Content-Type": "application/json" });
            res.end(JSON.stringify({ error: "Not Found" }));
        }

    } catch (err) {
        res.writeHead(500, { "Content-Type": "application/json" });
        res.end(JSON.stringify({ error: "Failed to load data" }));
    }

}).listen(PORT, () => {
    console.log(`Server running at http://localhost:${PORT}`);
});

