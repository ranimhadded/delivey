import React, { useState, useEffect } from "react";
import { useLocation } from "react-router-dom";

const Pay = () => {
  const [company, setCompany] = useState("");
  const [totalDept, setTotalDept] = useState(0);
  const [totalPayed, setTotalPayed] = useState(0);

  const useQuery = () => {
    return new URLSearchParams(useLocation().search);
  };

  const query = useQuery();

  useEffect(() => {
    const fetchData = async () => {
      try {
        const email = query.get("email");
        console.log("Fetching data for email:", email);
    
        const response = await fetch(`http://localhost:9006/api/company?email=${email}`);
        if (!response.ok) {
          throw new Error("Network response was not ok");
        }
        const data = await response.text();
        console.log("Fetched data:", data);
        setCompany(data);
        
        const responseDept = await fetch(`http://localhost:9006/api/totaldept?email=${email}`);
        if (!responseDept.ok) {
          throw new Error("Network response was not ok");
        }
        const totalDeptData = await responseDept.json();
        setTotalDept(totalDeptData);
        
        const responsePayed = await fetch(`http://localhost:9006/api/totalpayed?email=${email}`);
        if (!responsePayed.ok) {
          throw new Error("Network response was not ok");
        }
        const totalPayedData = await responsePayed.json();
        setTotalPayed(totalPayedData);
      } catch (error) {
        console.error("Error fetching data:", error);
      }
    };

    fetchData();
  }, [query]);

  return (
    <>
      <section id="content">
        <main>
          <div className="table-data">
            <div className="order">
              <div className="head">
                <h3>Recent Orders</h3>
                <i className="bx bx-search"></i>
                <i className="bx bx-filter"></i>
              </div>
              <h2>Payment</h2>
              <table>
                <thead>
                  <tr>
                    <th>Expidateur</th>
                    <th>Montant</th>
                    <th>Ref</th>
                    <th>Etat</th>
                    <th>nb pack</th>
                    <th>Avance</th>
                  </tr>
                </thead>
                <tbody>
                  <tr>
                    <td>{company}</td>
                    <td>{totalPayed}</td>
                    <td>-</td>
                    <td>non validate</td>
                    <td>-</td>
                    <td>-</td>
                  </tr>
                  <tr>
                    <td>{company}</td>
                    <td>{totalDept}</td>
                    <td>-</td>
                    <td>validate</td>
                    <td>-</td>
                    <td>-</td>
                  </tr>
                </tbody>
              </table>
            </div>
          </div>
        </main>
      </section>
    </>
  );
};

export default Pay;