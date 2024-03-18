import BasicLayout from "../layouts/BasicLayout";
import { useState, useEffect } from "react";
import axios from "axios";
import ListComponent_c from "../components/c_products/ListComponent_c";

const MainPage = () => {
  const [products, setProducts] = useState([]);
  const [selectedProduct, setSelectedProduct] = useState(null);

  useEffect(() => {
    const fetchProducts = async () => {
      try {
        const response = await axios.get("/api/products");
        setProducts(response.data);
      } catch (error) {
        console.error("제품 정보를 가져오는 중 오류가 발생했습니다:", error);
      }
    };

    fetchProducts();
  }, []);

  const handleProductClick = (product) => {
    setSelectedProduct(product);
  };

  return (
    <BasicLayout>
      <div className="text-3xl">Main Page</div>
      <div style={{ display: 'grid', gridTemplateColumns: 'repeat(4, 1fr)', gap: '20px', margin: '0 auto'}}>
        {products.slice(0, 16).map((product, index) => (
          <div key={index} className="border border-gray-300 p-4" onClick={() => handleProductClick(product)}>
            <img src={product.imageUrl} alt={`제품 이미지 ${index}`} className="mb-2" />
            <div className="font-bold">{product.name}</div>
            <div>{product.price}원</div>
          </div>
        ))}
      </div>
      {selectedProduct && (
        <ListComponent_c selectedProduct={selectedProduct} />
      )}
    </BasicLayout>
  );
};

export default MainPage;
