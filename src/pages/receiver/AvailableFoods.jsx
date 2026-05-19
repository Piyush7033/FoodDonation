import React, { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import API from '../../api/axios';
import '../../styles/receiver.css';

const AvailableFoods = () => {

  const [foods, setFoods] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState("");

  const [requestingId, setRequestingId] = useState(null);
  const [openFormId, setOpenFormId] = useState(null); // 👈 open card form
  const [message, setMessage] = useState(""); // 👈 receiver message

  const navigate = useNavigate();

  // FETCH FOODS
  const fetchFoods = async () => {
    try {
      setLoading(true);
      setError("");

      const res = await API.get("/food/available");
      setFoods(res.data || []);

    } catch (error) {
      setError(error.response?.data?.message || "Failed to load foods");
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    fetchFoods();
  }, []);

  // OPEN FORM
  const openRequestForm = (foodId) => {
    setOpenFormId(foodId);
    setMessage("");
  };

  // CLOSE FORM
  const closeForm = () => {
    setOpenFormId(null);
    setMessage("");
  };

  // SEND REQUEST
  const handleRequest = async (foodId) => {

    if (!message.trim()) {
      alert("Please write a message");
      return;
    }

    try {
      setRequestingId(foodId);

      const token = localStorage.getItem("authToken");

      const res = await API.post(
        `/request/create/${foodId}`,
        message,
        {
          headers: {
            Authorization: `Bearer ${token}`,
            "Content-Type": "application/json"
          }
        }
      );

      console.log("Request Success:", res.data);

      alert("Food Requested Successfully!");

      closeForm();
      fetchFoods();

      // optional redirect
      navigate("/my-requests");

    } catch (error) {
      alert(error.response?.data || "Failed to request food");
    } finally {
      setRequestingId(null);
    }
  };

  return (
    <div className="receiver-container">

      <h2>🍱 Available Foods</h2>

      {loading && <p>Loading foods...</p>}

      {error && <p className="error-text">{error}</p>}

      {!loading && !error && foods.length === 0 && (
        <p>No food available right now</p>
      )}

      {!loading && foods.length > 0 && (
        <div className="food-grid">

          {foods.map((food) => (
            <div key={food.id} className="food-card">

              <h3>{food.title}</h3>
              <p>{food.description}</p>

              <p><strong>Quantity:</strong> {food.quantity}</p>
              <p><strong>Location:</strong> {food.location}</p>
              <p><strong>Status:</strong> {food.status}</p>

              <p>
                <strong>Expiry:</strong>{" "}
                {food.expiryTime
                  ? new Date(food.expiryTime).toLocaleString()
                  : "N/A"}
              </p>

              {/* BUTTON */}
              <button
                className="btn"
                onClick={() => openRequestForm(food.id)}
              >
                View & Request
              </button>

              {/* ========================= */}
              {/* REQUEST FORM (INLINE CARD) */}
              {/* ========================= */}

              {openFormId === food.id && (
                <div className="request-box">

                  <textarea
                    placeholder="Write your message to donor..."
                    value={message}
                    onChange={(e) => setMessage(e.target.value)}
                  />

                  <div style={{ marginTop: "10px" }}>

                    <button
                      onClick={() => handleRequest(food.id)}
                      disabled={requestingId === food.id}
                    >
                      {requestingId === food.id
                        ? "Sending..."
                        : "Send Request"}
                    </button>

                    <button
                      onClick={closeForm}
                      style={{ marginLeft: "10px" }}
                    >
                      Cancel
                    </button>

                  </div>

                </div>
              )}

            </div>
          ))}

        </div>
      )}

    </div>
  );
};

export default AvailableFoods;