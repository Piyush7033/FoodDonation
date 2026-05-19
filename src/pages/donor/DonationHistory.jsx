import React, { useEffect, useState } from "react";
import API from "../../api/axios";

const DonationHistory = () => {

  // =========================================
  // STATES
  // =========================================

  const [donations, setDonations] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState("");

  // =========================================
  // LOAD DATA
  // =========================================

  useEffect(() => {

    fetchDonationHistory();

  }, []);

  // =========================================
  // FETCH DONATION HISTORY
  // =========================================

  const fetchDonationHistory = async () => {

    try {

      setLoading(true);
      setError("");

      // =========================================
      // GET TOKEN
      // =========================================

      const token =
        localStorage.getItem("token");

      console.log("🔑 TOKEN:", token);

      // =========================================
      // TOKEN CHECK
      // =========================================

      if (!token) {

        setError(
          "Authentication token not found"
        );

        return;
      }

      // =========================================
      // API REQUEST
      // =========================================

      const response = await API.get(
        "/donations/my",
        {
          headers: {
            Authorization: `Bearer ${token}`
          }
        }
      );

      console.log(
        "📦 DONATION RESPONSE:",
        response.data
      );

      // =========================================
      // SAFE ARRAY CHECK
      // =========================================

      const donationData =
        Array.isArray(response.data)
          ? response.data
          : [];

      setDonations(donationData);

    } catch (error) {

      console.error(
        "❌ Donation Error:",
        error.response?.data || error.message
      );

      console.log(
        "❌ Full Error:",
        error.response
      );

      // =========================================
      // ERROR HANDLING
      // =========================================

      if (error.response?.status === 401) {

        setError(
          "Unauthorized. Please login again."
        );

      } else if (
        error.response?.status === 403
      ) {

        setError(
          "Access denied."
        );

      } else if (
        error.response?.status === 404
      ) {

        setError(
          "API endpoint not found."
        );

      } else {

        setError(
          error.response?.data?.message ||
          "Failed to load donations"
        );
      }

    } finally {

      setLoading(false);
    }
  };

  return (

    <div className="donor-history">

      {/* ========================================= */}
      {/* TITLE */}
      {/* ========================================= */}

      <h2>
        📜 Donation History
      </h2>

      {/* ========================================= */}
      {/* LOADING */}
      {/* ========================================= */}

      {loading && (

        <p className="loading-text">
          Loading donations...
        </p>

      )}

      {/* ========================================= */}
      {/* ERROR */}
      {/* ========================================= */}

      {!loading && error && (

        <p
          className="error-text"
          style={{ color: "red" }}
        >
          {error}
        </p>

      )}

      {/* ========================================= */}
      {/* EMPTY */}
      {/* ========================================= */}

      {!loading &&
        !error &&
        donations.length === 0 && (

        <p className="empty-text">
          No donations found.
        </p>

      )}

      {/* ========================================= */}
      {/* DONATION LIST */}
      {/* ========================================= */}

      {!loading &&
        !error &&
        donations.length > 0 && (

        <div className="history-container">

          {donations.map((donation) => (

            <div
              className="history-card"
              key={donation.id}
            >

              {/* FOOD NAME */}
              <h3>
                {donation.foodName ||
                  "Food Donation"}
              </h3>

              {/* LOCATION */}
              <p>
                <strong>Location:</strong>{" "}
                {donation.location || "N/A"}
              </p>

              {/* QUANTITY */}
              <p>
                <strong>Quantity:</strong>{" "}
                {donation.quantity || "N/A"}
              </p>

              {/* STATUS */}
              <p>

                <strong>Status:</strong>{" "}

                <span
                  className={`status ${
                    donation.status === "ACCEPTED"
                      ? "status-available"
                      : donation.status === "REJECTED"
                      ? "status-donated"
                      : "status-pending"
                  }`}
                >
                  {donation.status}
                </span>

              </p>

              {/* DONOR EMAIL */}
              <p>
                <strong>Donor Email:</strong>{" "}
                {donation.donorEmail || "N/A"}
              </p>

              {/* DONATED DATE */}
              <p>

                <strong>Donated At:</strong>{" "}

                {donation.donatedAt
                  ? new Date(
                      donation.donatedAt
                    ).toLocaleString()
                  : "N/A"}

              </p>

            </div>

          ))}

        </div>

      )}

    </div>
  );
};

export default DonationHistory;