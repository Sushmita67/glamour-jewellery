import { Carousel } from "react-responsive-carousel";

import JewelleryBanner1 from "../../assets/home-banner/jewellery-1.jpg";
import JewelleryBanner2 from "../../assets/home-banner/jewellery-2.jpg";
import JewelleryBanner3 from "../../assets/home-banner/jewellery-3.jpg";

import styles from "react-responsive-carousel/lib/styles/carousel.min.css";
import componentStyles from "./style.module.css";


export const BannerCarousel = () => {
  return (
    <section className={componentStyles.section}>
      <Carousel
        autoPlay
        infiniteLoop
        className={styles}
        showArrows={false}
        showStatus={false}
        showThumbs={false}
      >
        <img src={JewelleryBanner1} alt="" />
        <img src={JewelleryBanner2} alt="" />
        <img src={JewelleryBanner3} alt="" />
      </Carousel>
    </section>
  );
}
