import React from "react";
import { useState } from "react";
import { useEffect } from "react";
import GoogleIcon from '@mui/icons-material/Google';
import FacebookRoundedIcon from '@mui/icons-material/FacebookRounded';
import TwitterIcon from '@mui/icons-material/Twitter';
import { useNavigate } from 'react-router-dom';


const Loginclient = () => {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const navigate = useNavigate();

  // Extract the email from the URL
  useEffect(() => {
    const urlParams = new URLSearchParams(window.location.search);
    const extractedEmail = urlParams.get('email');
    if (extractedEmail) {
      setEmail(extractedEmail);
    }
  }, []);

  const handleSubmit = async (e) => {
    if (e) {
      e.preventDefault();
    }

    const response = await fetch("http://localhost:9006/api/client/login", {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify({ email, password })
    });
    if (response.ok) {
      const client = await response.json();
      localStorage.setItem('client', JSON.stringify(client));
      
      // Add the email parameter to the URL before navigating
      navigate(`/client?email=${email}`);
    } else {
      console.log('Login failed');
    }
  };
    return (
      <>
        <form method="POST" class="register-form" id="login-form" >
          <div class="form-group">
            <label for="your_name"><i class="zmdi zmdi-account material-icons-name"></i></label>
            <input type="text" name="email" id="email" placeholder="Your Email" value={email} onChange={(e)=>setEmail(e.target.value)} />
          </div>
          <div class="form-group">
            <label for="your_pass"><i class="zmdi zmdi-lock"></i></label>
            <input type="password" name="password" id="password" placeholder="Password" value={password} onChange={(e)=>setPassword(e.target.value)} />
          </div>
          <div class="form-group">
            <input type="checkbox" name="remember-me" id="remember-me" class="agree-term" />
            <label for="remember-me" class="label-agree-term"><span><span></span></span>Remember me</label>
          </div>
          <div class="form-group form-button">
          <input type="submit" name="signin" id="signin" class="form-submit" value="Log in" onClick={(e) => handleSubmit(e)} />


          </div>
        </form>
        <div class="social-login">
          <span class="social-label">Or login with</span>
          <ul class="socials">
            <li><a href="#"><FacebookRoundedIcon>Facebook</FacebookRoundedIcon></a></li>
            <li><a href="#"><TwitterIcon>Twitter</TwitterIcon></a></li>
            <li><a href="#"><GoogleIcon>Google</GoogleIcon></a></li>
          </ul>
        </div>
      </>
    );
  };
  
  export default Loginclient;
  