import React from "react";
import './client.css';
import Dashboard from "./dashboard";
import Addpack from "./packages/addpack";
import Checkpack from "./packages/checkpack";
import Managepack from "./packages/managepack";
import Pay from "./pay";
import Return from "./return";
import Setting from "./setting";
import Profile from "./profile";
export default function Sidebar(props) {
  const handleMenuItemClick = (component) => {
    props.onMenuItemClick(component);
  };
    window.onload = function() {
        let arrow = document.querySelectorAll(".arrow");
        for (var i = 0; i < arrow.length; i++) {
          arrow[i].addEventListener("click", (e)=>{
         let arrowParent = e.target.parentElement.parentElement;//selecting main parent of arrow
         arrowParent.classList.toggle("showMenu");
          });
        }
        let sidebar = document.querySelector(".sidebar");
        let sidebarBtn = document.querySelector(".bx-menu");
        console.log(sidebarBtn);
        sidebarBtn.addEventListener("click", ()=>{
          sidebar.classList.toggle("close");
        });}
return(
    <>
<link href='https://unpkg.com/boxicons@2.0.7/css/boxicons.min.css' rel='stylesheet'></link>

<body>
  
    
  <div className="sidebar close">
    <div className="logo-details">
      
      <span className="logo_name"></span>
    </div>
    <ul className="nav-links">
      <li>
        <a onClick={() => handleMenuItemClick(<Dashboard />)}>
          <i class='bx bx-grid-alt' ></i>
          <span className="link_name">Dashboard</span>
        </a>
        <ul className="sub-menu blank">
          <li>
            <a onClick={() => handleMenuItemClick(<Dashboard />)}
               className="link_name" >Dashboard</a>
          </li>
        </ul>
      </li>
      <li>
        <div className="iocn-link">
          <a href="#">
            <i class='bx bx-collection' ></i>
            <span className="link_name">Packages</span>
          </a>
          <i class='bx bxs-chevron-down arrow' ></i>
        </div>
        <ul className="sub-menu">
          <li><a className="link_name" href="#">My Packages</a></li>
          <li><a onClick={() => handleMenuItemClick(<Addpack />)}>Add a package</a></li>
          <li><a onClick={() => handleMenuItemClick(<Managepack />)}>Manage Packages</a></li>
          <li><a onClick={() => handleMenuItemClick(<Checkpack/>)}>Check Packages</a></li>
        </ul>
      </li>
      <li>
        <div className="iocn-link">
          <a href="#">
            <i class='bx bx-book-alt' ></i>
            <span className="link_name">Payment</span>
          </a>
          <i class='bx bxs-chevron-down arrow' ></i>
        </div>
        <ul className="sub-menu">
          <li><a className="link_name" href="#">Payment</a></li>
          <li><a onClick={() => handleMenuItemClick(<Pay />)}>Manage Payment</a></li>
      
        </ul>
      </li>
      <li>
        <a onClick={() => handleMenuItemClick(<Return />)}>
          <i class='bx bx-pie-chart-alt-2' ></i>
          <span className="link_name">Returned</span>
        </a>
        <ul className="sub-menu blank">
          <li><a className="link_name" onClick={() => handleMenuItemClick(<Return />)}>Returned</a></li>
        </ul>
      </li>
      <li>
        <a onClick={() => handleMenuItemClick(<Setting />)}>
          <i class='bx bx-cog' ></i>
          <span className="link_name">Setting</span>
        </a>
        <ul className="sub-menu blank">
          <li><a className="link_name" onClick={() => handleMenuItemClick(<Setting />)}>Setting</a></li>
        </ul>
      </li>



      
      <li>
    <div className="profile-details">
      <div className="profile-content">
      <img src="assets/profile.png" alt="profileImg" onClick={() => handleMenuItemClick(<Profile />)}></img> 
      </div>
      <div className="name-job">
        <div className="profile_name">ranimhaddad</div>
        <div className="job">Web Desginer</div>
      </div>
      <i class='bx bx-log-out' ></i>
    </div>
  </li>
</ul>
  </div>
  <section className="home-section">
    <div className="home-content">
      <i class='bx bx-menu' ></i>
      <span className="text"></span>
    </div>
  </section>
</body>
</>
    )
}
