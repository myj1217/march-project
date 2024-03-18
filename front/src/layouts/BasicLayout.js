import BasicMenu from "../components/menus/BasicMenu";
import CartComponent from "../components/menus/CartComponent";

const BasicLayout = ({ children }) => {
  return (
    <>
      {/* 기존 헤더 대신 BasicMenu*/}
      <BasicMenu />

      {/* 상단 여백 my-5 제거 */}
      <div style={{background: '#191c22', minHeight: '100vh'}}>
        <main className="container">
          {/* 상단 여백 py-40 변경 flex 제거 */}
          {children}
        </main>

        <aside className="container">
          
          <CartComponent />
        </aside>
      </div>
    </>
  );
};

export default BasicLayout;
