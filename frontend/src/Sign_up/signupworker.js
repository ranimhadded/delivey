import React, { useEffect, useState } from "react";
import Helmet  from "react-helmet";
import './signup.css';
import Header from "../Homepage/Header/header";
const Signupworker = () => {
    const[livName,setLivName]=useState('')
        const[email,setEmail]=useState('')
        const[pass,setPass]=useState('')
        const[position,setPosition]=useState('')
        const handleClick=(e)=>{
            e.preventDefault()
            const livreur={livName,email,pass,position}
            console.log(livreur)
            fetch("http://localhost:9006/api/livreur/add",{
            method:"POST",headers:{"content-Type":"application/json"},body:JSON.stringify(livreur)})
            .then(()=>{
                console.log("livreur added")
            })

        }
    useEffect(() => {
        const isRefreshed = localStorage.getItem("isRefreshed");
    
        if (!isRefreshed) {
          localStorage.setItem("isRefreshed", true);
          window.location.reload();
        } else {
          localStorage.removeItem("isRefreshed");
        }
      }, []);
  return (
       <>
       <Helmet>
            <title>Sign Up</title>
            <link rel='stylesheet' id='hfe-style-css'  href='https://kitpro.site/delivey/wp-content/plugins/header-footer-elementor/assets/css/header-footer-elementor.css?ver=1.6.9' type='text/css' media='all' />
            <link rel='stylesheet' id='swiper-css'  href='https://kitpro.site/delivey/wp-content/plugins/elementor/assets/lib/swiper/css/swiper.min.css?ver=5.3.6' type='text/css' media='all' />
            <link rel='stylesheet' id='elementor-frontend-css'  href='https://kitpro.site/delivey/wp-content/plugins/elementor/assets/css/frontend-lite.min.css?ver=3.11.2' type='text/css' media='all' />
            <link rel='stylesheet' id='elementor-post-6-css'  href='https://kitpro.site/delivey/wp-content/uploads/sites/70/elementor/css/post-6.css?ver=1678161082' type='text/css' media='all' />
            <link rel='stylesheet' id='hfe-widgets-style-css'  href='https://kitpro.site/delivey/wp-content/plugins/header-footer-elementor/inc/widgets-css/frontend.css?ver=1.6.9' type='text/css' media='all' />
            <link rel='stylesheet' id='elementor-post-270-css'  href='https://kitpro.site/delivey/wp-content/uploads/sites/70/elementor/css/post-270.css?ver=1678161082' type='text/css' media='all' />
            <link rel='stylesheet' id='hello-elementor-css'  href='https://kitpro.site/delivey/wp-content/themes/hello-elementor/style.min.css?ver=2.4.1' type='text/css' media='all' />
       </Helmet>
       <div class="elementor-section elementor-top-section elementor-element elementor-element-3c97ff9a elementor-reverse-mobile elementor-section-boxed elementor-section-height-default elementor-section-height-default" data-id="3c97ff9a" data-element_type="section" data-settings="{&quot;background_background&quot;:&quot;classic&quot;}">
			    <div class="elementor-background-overlay"></div>
				<div class="elementor-container elementor-column-gap-default"></div>
                
                </div>
                <Header></Header>
       <div className="main">
       <section className="signup">
            <div className="container">
                <div className="signup-content">
                    <div className="signup-form">
                        <h2 className="form-title">Sign up</h2>
                        <form method="POST" className="register-form" id="register-form">
                            <div className="form-group">
                                <label htmlFor="name"><i className="zmdi zmdi-account material-icons-name"></i></label>
                                <input type="text" name="livName" id="livName" placeholder="Your Name" value={livName} onChange={(e) => setLivName(e.target.value)}/>
                            </div>
                            <div className="form-group">
                                <label htmlFor="email"><i className="zmdi zmdi-email"></i></label>
                                <input type="email" name="email" id="email" placeholder="Your Email" value={email} onChange={(e) => setEmail(e.target.value)}/>
                            </div>
                            <div className="form-group">
                                <label htmlFor="pass"><i className="zmdi zmdi-lock"></i></label>
                                <input type="password" name="pass" id="pass" placeholder="Password" value={pass} onChange={(e) => setPass(e.target.value)}/>
                            </div>
                            <div className="form-group">
                                <label htmlFor="re-pass"><i className="zmdi zmdi-lock-outline"></i></label>
                                <input type="password" name="re_pass" id="re_pass" placeholder="Repeat your password"/>
                            </div>
                            <div className="form-group">
                                <label htmlFor="phone"><i className="zmdi zmdi-account material-icons-name"></i></label>
                                <input type="Number" name="name" id="name" placeholder="Phone number"/>
                            </div>
                            <div className="form-group">
                               <select value={position} onChange={(e) => setPosition(e.target.value)}>
                               <option value="" disabled selected hidden>Join the crew as</option>
                               <option value="volvo">Motocycle Rider</option>
                               <option value="saab">Pickup driver</option>
                               <option value="mercedes">Swift Biker</option>
                                </select> 
                               
                            </div>
                            <div className="form-group">
                                <input type="checkbox" name="agree-term" id="agree-term" className="agree-term" />
                                <label htmlFor="agree-term" className="label-agree-term"><span><span></span></span>I agree all statements in  <a href="#" className="term-service">Terms of service</a></label>
                            </div>
                            <div className="form-group form-button">
                                <input type="submit" name="signup" id="signup" className="form-submit" value="Register" onClick={handleClick}/>
                            </div>
                        </form>
                    </div>
                    <div className="signup-image">
                        <div>
                       <figure><img src="assets/giphy.gif" alt="sing up image"/></figure>
                       </div><br></br>
                        <a href="/login" className="signup-image-link">I am already member</a>
                    </div>
                </div>
            </div>
        </section>

       </div>
       </>
    
  );
}

export default Signupworker;