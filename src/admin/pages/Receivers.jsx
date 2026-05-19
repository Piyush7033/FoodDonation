import React, { useEffect, useState } from 'react';

import Sidebar from '../components/Sidebar';
import Navbar from '../components/Navbar';

import api from '../../services/api';

import './Receivers.css';

const Receivers = () => {

  // =========================================
  // STATES
  // =========================================

  const [receivers, setReceivers] = useState([]);

  const [loading, setLoading] = useState(true);

  const [receiverRequests, setReceiverRequests] =
    useState([]);

  const [requestLoading, setRequestLoading] =
    useState(false);

  const [error, setError] = useState("");

  // =========================================
  // FETCH RECEIVERS
  // =========================================

  const fetchReceivers = async () => {

    try {

      setLoading(true);

      const token =
        localStorage.getItem("authToken");

      const response = await api.get(
        "/admin/receivers",
        {
          headers: {
            Authorization: `Bearer ${token}`
          }
        }
      );

      console.log(
        "Receivers Data:",
        response.data
      );

      setReceivers(response.data || []);

    } catch (error) {

      console.error(
        "Receiver Fetch Error:",
        error.response?.data || error.message
      );

      setError(
        error.response?.data?.message ||
        "Failed to load receivers"
      );

    } finally {

      setLoading(false);
    }
  };

  // =========================================
  // FETCH ALL REQUESTS
  // =========================================

  const fetchReceiverRequests = async () => {

    try {

      setRequestLoading(true);

      setError("");

      const token =
        localStorage.getItem("authToken");

      // ✅ CORRECT API
      const response = await api.get(
        "/request/all",
        {
          headers: {
            Authorization: `Bearer ${token}`
          }
        }
      );

      console.log(
        "Receiver Requests:",
        response.data
      );

      setReceiverRequests(
        response.data || []
      );

    } catch (error) {

      console.error(
        "Request Fetch Error:",
        error.response?.data || error.message
      );

      setError(
        error.response?.data?.message ||
        "Failed to load requests"
      );

    } finally {

      setRequestLoading(false);
    }
  };

  // =========================================
  // LOAD DATA
  // =========================================

  useEffect(() => {

    fetchReceivers();

    fetchReceiverRequests();

  }, []);

  // =========================================
  // DELETE RECEIVER
  // =========================================

  const deleteReceiver = async (id) => {

    try {

      const token =
        localStorage.getItem("authToken");

      await api.delete(
        `/admin/users/${id}`,
        {
          headers: {
            Authorization: `Bearer ${token}`
          }
        }
      );

      alert(
        "Receiver deleted successfully"
      );

      fetchReceivers();

    } catch (error) {

      console.error(
        "Delete Error:",
        error.response?.data || error.message
      );

      alert(
        error.response?.data?.message ||
        "Failed to delete receiver"
      );
    }
  };

  // =========================================
  // ACCEPT REQUEST
  // =========================================

  const acceptRequest = async (id) => {

    try {

      const token =
        localStorage.getItem("authToken");

      const response = await api.put(
        `/request/${id}/accept`,
        {},
        {
          headers: {
            Authorization: `Bearer ${token}`
          }
        }
      );

      console.log(
        "Accept Response:",
        response.data
      );

      alert("Request accepted successfully");

      // REFRESH TABLE
      fetchReceiverRequests();

    } catch (error) {

      console.error(
        "Accept Error:",
        error.response?.data || error.message
      );

      alert(
        error.response?.data?.message ||
        "Failed to accept request"
      );
    }
  };

  // =========================================
  // REJECT REQUEST
  // =========================================

  const rejectRequest = async (id) => {

    try {

      const token =
        localStorage.getItem("authToken");

      const response = await api.put(
        `/request/${id}/reject`,
        {},
        {
          headers: {
            Authorization: `Bearer ${token}`
          }
        }
      );

      console.log(
        "Reject Response:",
        response.data
      );

      alert("Request rejected successfully");

      // REFRESH TABLE
      fetchReceiverRequests();

    } catch (error) {

      console.error(
        "Reject Error:",
        error.response?.data || error.message
      );

      alert(
        error.response?.data?.message ||
        "Failed to reject request"
      );
    }
  };

  return (

    <div className="admin-layout">

      {/* SIDEBAR */}
      <Sidebar />

      {/* MAIN */}
      <div className="admin-main">

        {/* NAVBAR */}
        <Navbar />

        {/* CONTENT */}
        <div className="admin-content">

          {/* ===================================== */}
          {/* RECEIVER SECTION */}
          {/* ===================================== */}

          <h1 className="receiver-page-title">
            All Receivers
          </h1>

          {loading ? (

            <p className="loading-text">
              Loading receivers...
            </p>

          ) : (

            <div className="receiver-table-container">

              <table className="receiver-table">

                <thead>

                  <tr>

                    <th>ID</th>
                    <th>Name</th>
                    <th>Email</th>
                    <th>Role</th>
                    <th>Status</th>
                    <th>Action</th>

                  </tr>

                </thead>

                <tbody>

                  {receivers.length > 0 ? (

                    receivers.map((receiver) => (

                      <tr key={receiver.id}>

                        <td>{receiver.id}</td>

                        <td>
                          {receiver.name || "N/A"}
                        </td>

                        <td>
                          {receiver.email}
                        </td>

                        <td>
                          {receiver.role}
                        </td>

                        <td>

                          <span
                            className={
                              receiver.active
                                ? "status-active"
                                : "status-disabled"
                            }
                          >
                            {receiver.active
                              ? "Active"
                              : "Disabled"}
                          </span>

                        </td>

                        <td>

                          <button
                            className="delete-btn"
                            onClick={() =>
                              deleteReceiver(receiver.id)
                            }
                          >
                            Delete
                          </button>

                        </td>

                      </tr>

                    ))

                  ) : (

                    <tr>

                      <td colSpan="6">
                        No receivers found
                      </td>

                    </tr>

                  )}

                </tbody>

              </table>

            </div>

          )}

          {/* ===================================== */}
          {/* REQUEST SECTION */}
          {/* ===================================== */}

          <h1 className="receiver-page-title mt-5">
            Receiver Food Requests
          </h1>

          {requestLoading && (

            <p className="loading-text">
              Loading requests...
            </p>

          )}

          {error && (

            <p className="error-text">
              {error}
            </p>

          )}

          {!requestLoading && !error && (

            <div className="receiver-table-container">

              <table className="receiver-table">

                <thead>

                  <tr>

                    <th>ID</th>

                    <th>Receiver Name</th>

                    <th>Food Title</th>

                    <th>Quantity</th>

                    <th>Location</th>

                    <th>Status</th>

                    <th>Action</th>

                  </tr>

                </thead>

                <tbody>

                  {receiverRequests.length > 0 ? (

                    receiverRequests.map((request) => (

                      <tr key={request.id}>

                        {/* REQUEST ID */}
                        <td>{request.id}</td>

                        {/* RECEIVER */}
                        <td>
                          {request.receiver?.name || "N/A"}
                        </td>

                        {/* FOOD TITLE */}
                        <td>
                          {request.food?.title || "N/A"}
                        </td>

                        {/* QUANTITY */}
                        <td>
                          {request.food?.quantity || "N/A"}
                        </td>

                        {/* LOCATION */}
                        <td>
                          {request.food?.location || "N/A"}
                        </td>

                        {/* STATUS */}
                        <td>

                          <span
                            className={
                              request.status === "APPROVED"
                                ? "status-active"
                                : request.status === "REJECTED"
                                ? "status-disabled"
                                : "status-pending"
                            }
                          >
                            {request.status}
                          </span>

                        </td>

                        {/* ACTION */}
                        <td>

                          {request.status === "REQUESTED" && (

                            <>
                              <button
                                className="accept-btn"
                                onClick={() =>
                                  acceptRequest(request.id)
                                }
                              >
                                Accept
                              </button>

                              <button
                                className="reject-btn"
                                onClick={() =>
                                  rejectRequest(request.id)
                                }
                              >
                                Reject
                              </button>
                            </>

                          )}

                        </td>

                      </tr>

                    ))

                  ) : (

                    <tr>

                      <td colSpan="7">
                        No requests found
                      </td>

                    </tr>

                  )}

                </tbody>

              </table>

            </div>

          )}

        </div>

      </div>

    </div>
  );
};

export default Receivers;