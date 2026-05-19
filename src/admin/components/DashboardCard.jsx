const DashboardCard = ({ title, count }) => {
  return (
    <div className="bg-white shadow-md rounded-lg p-5 border">
      <h2 className="text-gray-500 text-lg">{title}</h2>
      <p className="text-3xl font-bold mt-2">{count}</p>
    </div>
  );
};

export default DashboardCard;