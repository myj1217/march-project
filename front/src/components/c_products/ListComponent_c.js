import { useEffect, useState } from "react";
import axios from "axios"; // axios 라이브러리 import
import useCustomMove from "../../hooks/useCustomMove";
import FetchingModal from "../common/FetchingModal";

import { API_SERVER_HOST } from "../../api/todoApi";
import PageComponent from "../common/PageComponent";
import useCustomLogin from "../../hooks/useCustomLogin";

const host = API_SERVER_HOST;

const initState = {
  dtoList: [],
  pageNumList: [],
  pageRequestDTO: null,
  prev: false,
  next: false,
  totalCount: 0,
  prevPage: 0,
  nextPage: 0,
  totalPage: 0,
  current: 0,
};

const ListComponent_c = () => {
  const { exceptionHandle } = useCustomLogin();
  const { page, size, refresh, moveToList, moveToRead } = useCustomMove();

  const [serverData, setServerData] = useState(initState);
  const [fetching, setFetching] = useState(false);
  const [selectedProduct, setSelectedProduct] = useState(null); // 선택한 상품의 상세 정보를 저장하는 상태 변수

  useEffect(() => {
    setFetching(true);

    // Axios를 사용하여 데이터를 가져옴
    axios.get(`/api/products?page=${page}&size=${size}`)
      .then((response) => {
        console.log(response.data);
        setServerData(response.data);
        setFetching(false);
      })
      .catch((error) => {
        console.error("데이터를 가져오는 중 오류가 발생했습니다:", error);
        setFetching(false);
        exceptionHandle(error); // 에러 처리 함수 호출
      });
  }, [page, size, refresh]);

  // 이미지 클릭 시 해당 상품의 상세 정보를 선택하여 모달에 표시하는 함수
  const handleProductClick = (product) => {
    setSelectedProduct(product);
  };

  return (
    <div className="border-2 border-blue-100 mt-10 mr-2 ml-2">
      {fetching ? <FetchingModal /> : null}

      <div className="flex flex-wrap mx-auto p-6">
        {serverData.dtoList.map((product) => (
          <div
            key={product.pno}
            className="w-1/2 p-1 rounded shadow-md border-2"
            onClick={() => handleProductClick(product)} // 이미지 클릭 시 상품 정보 선택
          >
            <div className="flex flex-col h-full">
              <div className="font-extrabold text-2xl p-2 w-full ">
                {product.pno}
              </div>
              <div className="text-1xl m-1 p-2 w-full flex flex-col">
                <div className="w-full overflow-hidden ">
                  <img
                    alt="product"
                    className="m-auto rounded-md w-60"
                    src={`${host}/api/products/view/s_${product.uploadFileNames[0]}`}
                  />
                </div>

                <div className="bottom-0 font-extrabold bg-white">
                  <div className="text-center p-1">이름: {product.pname}</div>
                  <div className="text-center p-1">가격: {product.price}</div>
                </div>
              </div>
            </div>
          </div>
        ))}
      </div>
      <PageComponent serverData={serverData} movePage={moveToList} />
      
      {/* 선택한 상품의 상세 정보를 표시하는 모달 */}
      {selectedProduct && (
        <div className="fixed inset-0 z-50 flex items-center justify-center bg-black bg-opacity-50">
          <div className="bg-white p-4 rounded-md">
            <div className="font-bold text-xl mb-2">상품 상세 정보</div>
            <div>상품 번호: {selectedProduct.pno}</div>
            <div>상품 이름: {selectedProduct.pname}</div>
            <div>상품 가격: {selectedProduct.price}</div>
          </div>
        </div>
      )}
    </div>
  );
};

export default ListComponent_c;
