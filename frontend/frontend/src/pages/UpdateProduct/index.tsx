import {UpdateProductForm} from "../../components/AddProductForm/UpdateProductForm";
import { PageTitle } from '../../components/PageTitle/index';
import styles from "./style.module.css";



export const UpdateProduct = () => {
  return (
    <main className="container-padding">
      <section className="max-width">
        <PageTitle title="Update Product" />
        <div className={`${styles.container} gray-box`}>
          <UpdateProductForm />
        </div>
      </section>
    </main>
  );
}
