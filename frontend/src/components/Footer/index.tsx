import styles from "./style.module.css";


export const Footer = () => {
  return (
    <footer className={`${styles.footer} container-padding`}>
      <div className={`${styles.copyrightContainer} max-width`}>
        <p>&copy; Developed By Susmita Biswakarma (220401) | Batch 33c | Softwarica College</p>
      </div>
    </footer >
  );
}
