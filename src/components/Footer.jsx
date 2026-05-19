import React from "react";
import "./Footer.css";

import {
  FaFacebookF,
  FaInstagram,
  FaTwitter,
  FaWhatsapp
} from "react-icons/fa";

const Footer = () => {
  return (
    <footer className="footer">

      <div className="footer-container">

        {/* Brand */}
        <section className="footer-brand">
          <h2>FoodDonate</h2>

          <p>
            Connecting surplus food with people in need.
            Reduce waste, share happiness.
          </p>
        </section>

        {/* Links */}
        <nav className="footer-links">
          <h3>Quick Links</h3>

          <a href="/">Home</a>
          <a href="/donor">Donor</a>
          <a href="/receiver">Receiver</a>
          <a href="/login">Login</a>
        </nav>

        {/* Support */}
        <nav className="footer-links">
          <h3>Support</h3>

          <a href="/">Help Center</a>
          <a href="/">Privacy Policy</a>
          <a href="/">Terms</a>
        </nav>

        {/* Social */}
        <section className="footer-social">
          <h3>Follow Us</h3>

          <div className="icons">

            <a href="#" aria-label="Facebook">
              <FaFacebookF />
            </a>

            <a href="#" aria-label="Instagram">
              <FaInstagram />
            </a>

            <a href="#" aria-label="Twitter">
              <FaTwitter />
            </a>

            <a href="#" aria-label="WhatsApp">
              <FaWhatsapp />
            </a>

          </div>
        </section>

      </div>

      {/* Bottom */}
      <p className="footer-bottom">
        © {new Date().getFullYear()} FoodDonate. All rights reserved.
      </p>

    </footer>
  );
};

export default Footer;