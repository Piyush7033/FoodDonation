import axios from "axios";

// ==========================================
// CREATE AXIOS INSTANCE
// ==========================================
const API = axios.create({
  baseURL: "http://localhost:8080/api",
  headers: {
    "Content-Type": "application/json",
  },
});

// ==========================================
// REQUEST INTERCEPTOR
// ==========================================
API.interceptors.request.use(
  (req) => {
    const token = localStorage.getItem("token");

    if (token) {
      req.headers.Authorization = `Bearer ${token}`;
    }

    console.log(
      `➡️ API REQUEST: ${req.method?.toUpperCase()} ${req.url}`
    );

    return req;
  },
  (error) => {
    console.error("❌ REQUEST ERROR:", error);
    return Promise.reject(error);
  }
);

// ==========================================
// RESPONSE INTERCEPTOR
// ==========================================
API.interceptors.response.use(
  (response) => {
    console.log(
      `⬅️ API RESPONSE: ${response.config.url} | ${response.status}`
    );

    return response;
  },
  (error) => {
    console.error("❌ API ERROR DETAILS:");

    console.log("URL:", error.config?.url);
    console.log("STATUS:", error.response?.status);
    console.log("DATA:", error.response?.data);
    console.log("MESSAGE:", error.message);

    // ======================================
    // AUTH ERROR HANDLING (SAFE VERSION)
    // ======================================
    if (error.response?.status === 401 || error.response?.status === 403) {
      console.warn("🔐 Auth error detected - clearing session");

      localStorage.removeItem("token");
      localStorage.removeItem("user");
      localStorage.removeItem("role");

      // ❌ NO REDIRECT HERE (important for your receiver issue)
      // Let React Router handle navigation
    }

    return Promise.reject(error);
  }
);

export default API;