import React, { useState, useEffect } from "react";
import Del from "./popupDelPack";
const List_package = () => {
    const [data, setData] = useState([]);
    const [idDelete, setIdDelete] = useState(null);
    const [isOpenDel, setIsOpenDel] = useState(false);
  
    const handleIdClickDelete = (id) => {
      setIdDelete(id);
    };
  
    useEffect(() => {
      const fetchData = async () => {
        try {
          const response = await fetch("http://localhost:9006/api/coliss");
          if (!response.ok) {
            throw new Error("Network response was not ok");
          }
          const data = await response.json();
          setData(data);
        } catch (error) {
          console.error("Error fetching data:", error);
        }
      };
  
      fetchData();
    }, [data]);
  
    return (
      <section id="content">
        <main>
          <div className="table-data">
            <div className="order">
              <div className="head">
                <h3>Packages list</h3>
                <i className="bx bx-search"></i>
                <i className="bx bx-filter"></i>
              </div>
              <table>
                <thead>
                  <tr>
                    <th>colis Id</th>
                    <th>ExpidateurName </th>
                    <th>Company</th>
                    <th>Price</th>
                    <th>nb piece</th>
                    <th>CreatedAt</th>
                    <th>ClientName </th>
                    <th>ClientState </th>
                    <th>Colis Status</th>
                    <th>Colis etat</th>
                    <th>Delete Colis</th>
                  </tr>
                </thead>
                <tbody>
                  {data.map((item) => (
                    <tr key={item.idColis}>
                      <td>{item.idColis} </td>
                      <td>{item.client ? item.client.clientName : ''}</td>
                      <td>{item.client ? item.client.company : ''}</td>
                      <td>{item.price}</td>
                      <td>{item.nbPiece}</td>
                      <td>{item.createdAt}</td>
                      <td>{item.clientName}</td>
                      <td>{item.clientState}</td>
                      <td>{item.status}</td>
                      <td>{item.etat}</td>
                      <td><span className="status pending" onClick={() => { setIsOpenDel(true); handleIdClickDelete(item.idColis); }}>Delete</span></td>
                    
                    </tr>
                  ))}
                </tbody>
              </table>
              {isOpenDel && <Del setIsOpenDel={setIsOpenDel} dataId= {idDelete}/>}
            </div>
          </div>
        </main>
      </section>
    );
  };
export default List_package;