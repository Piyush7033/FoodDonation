import React, { useState } from 'react';
import '../../styles/donor.css';
import API from '../../api/axios';

const AddFood = () => {

  const [food, setFood] = useState({
    title: '',
    description: '',
    quantity: '',
    location: '',
    expiryTime: ''
  });

  // ================= HANDLE INPUT CHANGE =================
  const handleChange = (e) => {

    setFood({
      ...food,
      [e.target.name]: e.target.value
    });
  };

  // ================= HANDLE FORM SUBMIT =================
  const handleSubmit = async (e) => {

    e.preventDefault();

    try {

      // GET TOKEN
      const token = localStorage.getItem("authToken");

      console.log("Token:", token);

      // FIX DATE FORMAT FOR LocalDateTime
      const payload = {
        ...food,
        expiryTime: food.expiryTime
          ? new Date(food.expiryTime).toISOString()
          : null
      };

      console.log("Payload:", payload);

      // API CALL
      const response = await API.post(
        "/food",
        payload,
        {
          headers: {
            Authorization: `Bearer ${token}`
          }
        }
      );

      console.log("Response:", response.data);

      alert("Food Added Successfully ✅");

      // OPTIONAL RESET FORM
      setFood({
        title: '',
        description: '',
        quantity: '',
        location: '',
        expiryTime: ''
      });

    } catch (error) {

      console.error("Add Food Error:", error);

      if (error.response?.status === 403) {

        alert("403 Forbidden: Token missing or invalid ❌");

      } else {

        alert(
          error.response?.data?.message ||
          "Failed To Add Food ❌"
        );
      }
    }
  };

  return (

    <div className="donor-form-container">

      <h2>➕ Add Food Donation</h2>

      <form
        onSubmit={handleSubmit}
        className="donor-form"
      >

        {/* FOOD TITLE */}
        <input
          type="text"
          name="title"
          placeholder="Food Name"
          value={food.title}
          onChange={handleChange}
          required
        />

        {/* DESCRIPTION */}
        <textarea
          name="description"
          placeholder="Description"
          value={food.description}
          onChange={handleChange}
          required
        />

        {/* QUANTITY */}
        <input
          type="text"
          name="quantity"
          placeholder="Quantity"
          value={food.quantity}
          onChange={handleChange}
          required
        />

        {/* LOCATION */}
        <input
          type="text"
          name="location"
          placeholder="Location"
          value={food.location}
          onChange={handleChange}
          required
        />

        {/* EXPIRY TIME */}
        <input
          type="datetime-local"
          name="expiryTime"
          value={food.expiryTime}
          onChange={handleChange}
          required
        />

        {/* SUBMIT BUTTON */}
        <button type="submit">
          Submit Donation
        </button>

      </form>

    </div>
  );
};

export default AddFood;