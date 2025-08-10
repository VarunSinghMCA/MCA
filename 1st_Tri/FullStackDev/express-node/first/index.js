import http from "http";

PORT = 8000;

const serve = http.createServer = () => {
    
}

serve.listen(PORT, ()=>{
    console.log("Server Listing to Port: ", PORT)
})