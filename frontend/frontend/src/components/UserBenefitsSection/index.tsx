import { FooterCard } from '../FooterCard/index';

import Account from "../../assets/Account.svg";
import Support from "../../assets/Support.svg";
import Saving from "../../assets/Saving.svg";

import styles from "./style.module.css";


export const UserBenefitsSection = () => {
  return (
    <div className={`${styles.cardsContainer} container-padding`}>
      <div className="max-width">
        <FooterCard
          imagePath={Support}
          imageAlt="Support"
          title="Customer Service"
          description="Wondering which piece of jewelry suits you? Try them all at the comfort of your home and return if you feel otherwise."
        />
        <FooterCard
          imagePath={Account}
          imageAlt="Account"
          title="Free Delivery inside Kathmandu"
          description="With big discounts, free delivery inside ringroad in Kathmandu."
        />
        <FooterCard
          imagePath={Saving}
          imageAlt="Saving"
          title="Amazing Savings"
          description="Get up to 30% off on our Products, and get your jewellery at an unbeatable price!"
        />
      </div>
    </div>
  );
}
