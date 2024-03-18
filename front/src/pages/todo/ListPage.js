import { useSearchParams } from "react-router-dom";
import ListComponent_c from "../../components/c_products/ListComponent_c";

const ListPage = () => {
  const [queryParams] = useSearchParams();

  const page = queryParams.get("page") ? parseInt(queryParams.get("page")) : 1;
  const size = queryParams.get("size") ? parseInt(queryParams.get("size")) : 10;

  return (
    <div className="p-4 w-full bg-white">
      <div className="text-3xl font-extrabold">Todo List Page Component</div>
      <ListComponent_c /> {/* 수정된 컴포넌트명으로 변경 */}
    </div>
  );
};

export default ListPage;
