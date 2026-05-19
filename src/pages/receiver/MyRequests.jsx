import React, { useEffect, useState } from 'react';
import API from '../../api/axios';
import '../../styles/receiver.css';

const MyRequests = () => {

  const [requests, setRequests] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState("");

  // FETCH REQUESTS
  const fetchRequests = async () => {
    try {
      setLoading(true);

      const res = await API.get("/request/my");

      setRequests(res.data || []);

    } catch (error) {
      console.log(error);
      setError("Failed to load requests");
    } finally {
      setLoading(false);
    }
  };

  // CANCEL REQUEST (FIXED → PUT)
  const cancelRequest = async (id) => {
    try {

      const confirmCancel = window.confirm(
        "Are you sure you want to cancel this request?"
      );

      if (!confirmCancel) return;

      const response = await API.put(
        `/request/${id}/cancel`
      );

      alert(response.data || "Request cancelled successfully");

      fetchRequests(); // refresh list

    } catch (error) {
      console.log(error);
      alert(
        error.response?.data || "Failed to cancel request"
      );
    }
  };

  useEffect(() => {
    fetchRequests();
  }, []);

  return (
    <div className="receiver-container">

      <h2>📋 My Requests</h2>

      {loading && <p>Loading requests...</p>}

      {error && <p className="error-text">{error}</p>}

      {!loading && requests.length === 0 && (
        <p>No requests found</p>
      )}

      {!loading && requests.length > 0 && (
        <div className="request-list">

          {requests.map((req) => (
            <div key={req.id} className="request-card">

              <h3>{req.food?.title || "Food Item"}</h3>

              <p><b>Description:</b> {req.food?.description}</p>
              <p><b>Quantity:</b> {req.food?.quantity}</p>
              <p><b>Location:</b> {req.food?.location}</p>

              <p>
                <b>Status:</b> {req.status}
              </p>

              <p>
                <b>Requested At:</b>{" "}
                {req.requestTime
                  ? new Date(req.requestTime).toLocaleString()
                  : "N/A"}
              </p>

              {/* CANCEL BUTTON */}
              {req.status === "REQUESTED" && (
                <button
                  className="cancel-btn"
                  onClick={() => cancelRequest(req.id)}
                >
                  Cancel Request
                </button>
              )}

            </div>
          ))}
        </div>
      )}
    </div>
  );
};

export default MyRequests;