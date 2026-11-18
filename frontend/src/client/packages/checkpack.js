import React, { useState, useEffect } from "react";
import  "../dash.css";
import Popup from "./AddPopup";
import Del from "./DelPopup";
import Edit from "./EditPopup";
import { useLocation } from 'react-router-dom';
const Checkpack = () => {
  const [isOpenPopup, setIsOpenPopup] = useState(false);
  const [isOpenDel, setIsOpenDel] = useState(false);
  const [isOpenEdit, setIsOpenEdit] = useState(false);
  const [data, setData] = useState([]);
  const [idEdit, setIdEdit] = useState(null);
  const [idDelete, setIdDelete] = useState(null);
  const [dataEdit, setDataEdit] = useState(null);

  const useQuery = () => {
    return new URLSearchParams(useLocation().search);
  };
  const query = useQuery();

  const handleIdClickEdit = (id) => {
    setIdEdit(id);
  };
  const handleIdClickDelete = (id) => {
    setIdDelete(id);
    setIdEdit(null);
  };

  
  useEffect(() => {
	
    if (idEdit) {
      fetch(`http://localhost:9006/api/find/${idEdit}`)
        .then(response => {
          if (!response.ok) {
            throw new Error('Network response was not ok');
          }
          return response.json();
        })
        .then(data => {
          setDataEdit(data);
        })
        .catch(error => {
          console.error('There was a problem with the fetch operation:', error);
        });
    }

    const fetchData = async () => {
      try {
        const email = query.get('email');
        const response = await fetch(`http://localhost:9006/api/colissbyemail?email=${email}`);
        if (!response.ok) {
          throw new Error('Network response was not ok');
        }
        const data = await response.json();
        setData(data);
      } catch (error) {
        console.error('Error fetching data:', error);
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
						<h3>Check Packs</h3>
            <i className='bx bx-plus' onClick={setIsOpenPopup.bind(this, true)} ></i>
            {isOpenPopup && <Popup setIsOpenPopup={setIsOpenPopup} />}
						<i className='bx bx-search' ></i>
						<i className='bx bx-filter' ></i>
					</div>
					<table>
						<thead>
							<tr>
								<th>Code</th>
								<th>Agence</th>
								<th>Order Date</th>
								<th>Nb Pickup</th>
                <th> Total</th>
								<th>Status</th>
                <th>Edit</th>
								<th>Delete</th>
							</tr>
						</thead>
						<tbody>
            {data && data.map(item => {
  let Class = null;
  let status = item.status;
  if (status === 'en cours') {
    Class = 'status completed';
  } else if (status === 'livré') {
    Class = 'status pending';
  } else if (status === 'returned') {
    Class = 'status process';
  }
  const isEditDisabled = status === 'livré' || status === 'returned';

  return (
    <tr key={item.idColis}>
      <td>{item.idColis}</td>
      <td>{item.clientCitie}</td>
      <td>01-10-2021</td>
      <td>{item.nbPiece}</td>
      <td>{item.price}</td>
      <td><span className={Class}>{item.status}</span></td>
      <td>
        <span
          className={`status ${isEditDisabled ? 'disabled' : 'pending'}`}
          onClick={() => { setIsOpenEdit(true); handleIdClickEdit(item.idColis); }}
          disabled={isEditDisabled}
        >
          Edit
        </span>
      </td>
      <td><span className="status pending" onClick={() => { setIsOpenDel(true); handleIdClickDelete(item.idColis); }}>Delete</span></td>
    </tr>
  );
})}

							
						</tbody>
						
					</table>
					{isOpenEdit && <Edit setIsOpenEdit={setIsOpenEdit} dataId= {dataEdit}  />}
          {isOpenDel && <Del setIsOpenDel={setIsOpenDel} dataId= {idDelete}/>}
				</div>
       
     </div>
    </main>
    </section>
  );
};
export default Checkpack;