import React, { useEffect, useState } from "react";
import API from "../../api/axios";

const MyDonations = () => {

  const [donations, setDonations] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState("");

  // =========================
  // EDIT STATE
  // =========================
  const [editId, setEditId] = useState(null);

  const [form, setForm] = useState({
    title: "",
    description: "",
    quantity: "",
    location: "",
    expiryTime: "",
  });

  const getToken = () => localStorage.getItem("authToken");

  // =========================
  // LOAD DATA
  // =========================
  useEffect(() => {
    fetchDonations();
  }, []);

  // =========================
  // FETCH DONATIONS
  // =========================
  const fetchDonations = async () => {
    try {
      setLoading(true);
      setError("");

      const response = await API.get("/food/my", {
        headers: {
          Authorization: `Bearer ${getToken()}`,
        },
      });

      setDonations(response.data || []);
    } catch (error) {
      setError("Failed to load donations");
    } finally {
      setLoading(false);
    }
  };

  // =========================
  // DELETE DONATION
  // =========================
  const deleteDonation = async (id) => {
    try {
      if (!window.confirm("Are you sure you want to delete this donation?")) return;

      await API.delete(`/food/${id}`, {
        headers: {
          Authorization: `Bearer ${getToken()}`,
        },
      });

      alert("Donation deleted successfully");
      fetchDonations();
    } catch (error) {
      alert(error.response?.data || "Failed to delete donation");
    }
  };

  // =========================
  // OPEN EDIT FORM
  // =========================
  const openEdit = (item) => {
    setEditId(item.id);

    // convert datetime for input field
    const formattedDate = item.expiryTime
      ? new Date(item.expiryTime).toISOString().slice(0, 16)
      : "";

    setForm({
      title: item.title || "",
      description: item.description || "",
      quantity: item.quantity || "",
      location: item.location || "",
      expiryTime: formattedDate,
    });
  };

  // =========================
  // HANDLE INPUT
  // =========================
  const handleChange = (e) => {
    setForm({
      ...form,
      [e.target.name]: e.target.value,
    });
  };

  // =========================
  // CANCEL EDIT
  // =========================
  const cancelEdit = () => {
    setEditId(null);
    setForm({
      title: "",
      description: "",
      quantity: "",
      location: "",
      expiryTime: "",
    });
  };

  // =========================
  // SAVE UPDATE
  // =========================
  const saveUpdate = async (id) => {
    try {

      await API.put(`/food/${id}`, form, {
        headers: {
          Authorization: `Bearer ${getToken()}`,
        },
      });

      alert("Updated successfully");

      setEditId(null);
      fetchDonations();

    } catch (error) {
      alert(error.response?.data || "Update failed");
    }
  };

  return (
    <div className="donor-list">

      <h2>🍛 My Donations</h2>

      {loading && <p>Loading...</p>}
      {error && <p style={{ color: "red" }}>{error}</p>}

      <div className="donation-history">

        {donations.map((item) => (
          <div key={item.id} className="donor-item">

            {/* ================= VIEW MODE ================= */}
            {editId !== item.id && (
              <>
                <h3>{item.title}</h3>

                <p><b>Description:</b> {item.description}</p>
                <p><b>Quantity:</b> {item.quantity}</p>
                <p><b>Location:</b> {item.location}</p>

                <p>
                  <b>Expiry:</b>{" "}
                  {item.expiryTime
                    ? new Date(item.expiryTime).toLocaleString()
                    : "N/A"}
                </p>

                <p><b>Status:</b> {item.status}</p>

                <div style={{ marginTop: "10px" }}>

                  <button
                    onClick={() => openEdit(item)}
                    style={{
                      marginRight: "10px",
                      background: "#2563eb",
                      color: "white",
                      padding: "6px 12px",
                      border: "none",
                      borderRadius: "5px",
                      cursor: "pointer",
                    }}
                  >
                    Update
                  </button>

                  <button
                    onClick={() => deleteDonation(item.id)}
                    style={{
                      background: "red",
                      color: "white",
                      padding: "6px 12px",
                      border: "none",
                      borderRadius: "5px",
                      cursor: "pointer",
                    }}
                  >
                    Delete
                  </button>

                </div>
              </>
            )}

            {/* ================= EDIT MODE ================= */}
            {editId === item.id && (
              <div style={{ display: "flex", flexDirection: "column", gap: "8px" }}>

                <input
                  name="title"
                  value={form.title}
                  onChange={handleChange}
                  placeholder="Title"
                />

                <input
                  name="description"
                  value={form.description}
                  onChange={handleChange}
                  placeholder="Description"
                />

                <input
                  name="quantity"
                  value={form.quantity}
                  onChange={handleChange}
                  placeholder="Quantity"
                />

                <input
                  name="location"
                  value={form.location}
                  onChange={handleChange}
                  placeholder="Location"
                />

                {/* ✅ EXPIRY DATE TIME (IMPORTANT FIX) */}
                <input
                  type="datetime-local"
                  name="expiryTime"
                  value={form.expiryTime}
                  onChange={handleChange}
                />

                <div style={{ marginTop: "10px" }}>

                  <button
                    onClick={() => saveUpdate(item.id)}
                    style={{
                      marginRight: "10px",
                      background: "green",
                      color: "white",
                      padding: "6px 12px",
                      border: "none",
                      borderRadius: "5px",
                      cursor: "pointer",
                    }}
                  >
                    Save
                  </button>

                  <button
                    onClick={cancelEdit}
                    style={{
                      background: "gray",
                      color: "white",
                      padding: "6px 12px",
                      border: "none",
                      borderRadius: "5px",
                      cursor: "pointer",
                    }}
                  >
                    Cancel
                  </button>

                </div>

              </div>
            )}

          </div>
        ))}

      </div>
    </div>
  );
};

export default MyDonations;