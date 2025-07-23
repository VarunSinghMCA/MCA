// import logo from './logo.svg';
import './App.css';


function App() {
  const data=[
  {
    title: "Card Title 1",
    at: "Card Subtitle",
    imgUrl: "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQUPIfiGgUML8G3ZqsNLHfaCnZK3I5g4tJabQ&s"
  },
  {
    title: "Card Title 2",
    at: "Card Subtitle",
    imgUrl: "https://images.ctfassets.net/hrltx12pl8hq/28ECAQiPJZ78hxatLTa7Ts/2f695d869736ae3b0de3e56ceaca3958/free-nature-images.jpg?fit=fill&w=1200&h=630"
  },
  {
    title: "Card Title 3",
    at: "Card Subtitle",
    imgUrl: "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQoFRQjM-wM_nXMA03AGDXgJK3VeX7vtD3ctA&s"
  }
];

  return (
    <div className="h-[100vh] flex justify-center items-center bg-gray-100 text-black">
      {
        data.map((item, i) => (
          <Card 
            key={i} 
            title={item.title} 
            at={item.at} 
            imgUrl={item.imgUrl} 
          />
        ))
      }
    </div>
  );
}

function Card({ title, at, imgUrl }) {
  return (
    <div className="flex flex-col items-center bg-white shadow-lg rounded-lg p-4 m-2">
      <img src={imgUrl} alt={title} className="w-32 h-32 object-cover rounded mb-3" />
      <h2 className="text-lg font-bold mb-1">{title}</h2>
      <p className="text-gray-500 text-sm">{at}</p>
    </div>
  );
}


export default App;
