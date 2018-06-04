<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>夏夜弥光</title>
<link href="css/bootstrap.css" rel="stylesheet" type="text/css" media="all" />
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="js/jquery.min.js"></script>
<!-- Custom Theme files -->
<!--theme-style-->
<link href="css/style.css" rel="stylesheet" type="text/css" media="all" />	
<!--//theme-style-->
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords" content="Fashion Store Responsive web template, Bootstrap Web Templates, Flat Web Templates, Andriod Compatible web template, 
Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyErricsson, Motorola web design" />
<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
<!--fonts-->
<link href='http://fonts.useso.com/css?family=Roboto:400,300,100,500,700,900' rel='stylesheet' type='text/css'>
<!--//fonts-->
<script type="text/javascript" src="js/move-top.js"></script>
<script type="text/javascript" src="js/easing.js"></script>
<script type="text/javascript">
					jQuery(document).ready(function($) {
						$(".scroll").click(function(event){		
							event.preventDefault();
							$('html,body').animate({scrollTop:$(this.hash).offset().top},1000);
						});
					});
					</script>
					<!----start-top-nav-script---->
</head>
<body> 
<!--header-->
	<div class="header" id="home">
			<div class="container">
				<div class="header-top">
					<div class="logo">
						<a href="index.html"><img src="images/logo.png" alt="" ></a>
					</div>
					<div class="top-nav">
					<span class="menu"> </span>
						<ul>
							<li class="active" ><a href="index.html" class="scroll">HOME</a></li>
							<li><a href="#about" class="scroll"> ABOUT</a></li>
							<li><a href="#work" class="scroll">WORK </a></li>
							<li><a href="#blog" class="scroll">BLOG </a></li>
							<li><a href="#contact" class="scroll">CONTACT </a></li>
						</ul>
						<script>
						$("span.menu").click(function(){
							$(".top-nav ul").slideToggle(500, function(){
							});
						});
				</script>
					</div>
				<div class="clearfix"> </div>
			</div>
			<div class="required">
				<input type="submit" value="REQUEST FOR QUOTE" >
			</div>
			<div class="clearfix"> </div>
		</div>
	</div>
	<!---->
	<div class="banner">
			<div class="banner-matter">
				<h2>A multi-talented freelance web desiger &amp; front-end developer</h2>
				<a href="#" class="down"><img src="images/load.png" alt="" >Download cv</a>								
			</div>
			<a href="#about" class="scroll arrow"><img src="images/arrow.png" alt=""></a>
	</div>
	<!--content-->
	<div class="content">	
			<div class="about" id="about">
				<div class="container">
					<h3>ABOUT</h3>
					<p class="dummy-about">Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled.</p>
					<p>Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries</p>
					<p>It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.</p>
					<p>Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.</p>
					<a href="#work" class="scroll down-in"><img src="images/down.png" alt=""></a>
				</div>
			</div>
			<!---->
			<div class="container">
			<div class="work" id="work">
				<h3>WORK</h3>
				<div class="work-in">
					<div class="work-grid">
						<a class="popup-with-zoom-anim" href="#small-dialog"><img class="img-responsive motor-in" src="images/pic.jpg" alt="" >
							<div class="simple">
						    	<h5>CRU<span>SCHIF</span>ORM</h5>
						    	<p>Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.	</p>					
						    </div></a>
					</div>
					<div class="work-grid work-grid1">
						<a class="popup-with-zoom-anim" href="#small-dialog"><img class="img-responsive " src="images/pic1.jpg" alt="" >
						<div class="simple simple-in">
						    	<h5>CRU<span>SCHIF</span>ORM</h5>
						    	<p>Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.</p>								
						    </div></a>
					</div>
					<div class="work-grid work-grid2">
						<a class="popup-with-zoom-anim" href="#small-dialog"><img class="img-responsive" src="images/pic2.jpg" alt="" >
						<div class="simple simple-out">
						    	<h5>CRU<span>SCHIF</span>ORM</h5>
						    	<p>Lorem Ipsum passages, and more recently with desktop.	</p>							
						    </div></a>
					</div>
					<div class=" work-grid3">
						<div class="work-bird">
							<a class="popup-with-zoom-anim" href="#small-dialog"><img class="img-responsive motor" src="images/pic3.jpg" alt="" >
							<div class="simple sim-2">
						    	<h5>CRU<span>SCHIF</span>ORM</h5>
						    	<p>Lorem Ipsum passages, and more recently with desktop.	</p>							
						    </div></a>
						</div>
						<div class="work-bird">
							<a class="popup-with-zoom-anim" href="#small-dialog"><img class="img-responsive work-on" src="images/pic7.jpg" alt="" >
							<div class="simple simple-out">
						    	<h5>CRU<span>SCHIF</span>ORM</h5>
						    	<p>Lorem Ipsum passages, and more recently with desktop.	</p>							
						    </div></a>
						</div>
					</div>
					<div class=" work-grid4">
					<div class="work-bird">
							<a class="popup-with-zoom-anim" href="#small-dialog"><img class="img-responsive " src="images/pic4.jpg" alt="" >
							<div class="simple simple-out">
						    	<h5>CRU<span>SCHIF</span>ORM</h5>
						    	<p>Lorem Ipsum passages, and more recently with desktop.	</p>							
						    </div></a>
						</div>
						<div class="work-bird">
							<a class="popup-with-zoom-anim" href="#small-dialog"><img class="img-responsive work-on" src="images/pic6.jpg" alt="" >
							<div class="simple simple-out">
						    	<h5>CRU<span>SCHIF</span>ORM</h5>
						    	<p>Lorem Ipsum passages, and more recently with desktop.	</p>							
						    </div></a>
						</div>
					</div>
					
					<div class="work-grid work-grid5">
						<a class="popup-with-zoom-anim" href="#small-dialog"><img class="img-responsive" src="images/pic5.jpg" alt="" >
						<div class="simple sim-1">
						    	<h5>CRU<span>SCHIF</span>ORM</h5>
						    	<p>Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.								
						    </div>
					</div>					
					<div class="clearfix"> </div>
				</div>
				<a href="#item" class="scroll blog-down"><img src="images/down.png" alt=""></a>
			</div>
		</div>
		<!---->
		<!-- caption-popup -->
		<div class="caption-popup">
				<div id="small-dialog" class="mfp-hide innercontent">
				<!---->
				<script src="js/responsiveslides.min.js"></script>
		  <script>
			// You can also use "$(window).load(function() {"
			$(function () {
			  // Slideshow 1
			  $("#slider1").responsiveSlides({
				 auto: true,
				 nav: true,
				 speed: 500,
				 namespace: "callbacks",
			  });
			});
		  </script>
					<div class="slider">
					<ul class="rslides" id="slider1">
					  <li><img src="images/ba1.jpg" alt=""></li>
					  <li><img src="images/ba1.jpg" alt=""></li>
					  <li><img src="images/ba1.jpg" alt=""></li>
					</ul>
					</div>
					<div class="sit">
						<h6>Typography</h6>
					<div class="sit-in">
						<h4 class="prome">Promesas de Tierra</h4>
						<ul class="circle-in">
							<li>HTML 5</li>
							<li>CSS3</li>
							<li>JQUREY</li>
						</ul>
					<div class="clearfix"> </div>
					</div>
					<div class="screen">
						<span>Creating bonds away from our screens</span>
						<p>There were several roads near by, but it did not take her long to find the one paved with yellow bricks. Within a short time she was walking briskly toward the Emerald City, her silver shoes tinkling merrily on the hard, yellow road-bed. The sun shone bright and the birds sang sweetly, and Dorothy did not feel nearly so bad as you might think a little girl would who had been suddenly whisked away from her own country and set down in the midst of a strange land.</p>
						<p>There were several roads near by, but it did not take her long to find the one paved with yellow bricks. Within a short time she was walking briskly toward the Emerald City, her silver shoes tinkling merrily on the hard, yellow road-bed. </p>
					</div>	
						<div class="near">
							<small class="near-left">04 December 2014</small>
							<a href="#" class="near-right"> SHARE</a>
							<div class="clearfix"> </div>
						</div>
					</div>
				</div>
				 <script>
						$(document).ready(function() {
						$('.popup-with-zoom-anim').magnificPopup({
							type: 'inline',
							fixedContentPos: false,
							fixedBgPos: true,
							overflowY: 'auto',
							closeBtnInside: true,
							preloader: false,
							midClick: true,
							removalDelay: 300,
							mainClass: 'my-mfp-zoom-in'
						});
																						
						});
				</script>								  
				</div>
			<!----//fea-video---->
		</div>
		<!---pop-up-box---->
				<link href="css/popuo-box.css" rel="stylesheet" type="text/css" media="all"/>
				<script src="js/jquery.magnific-popup.js" type="text/javascript"></script>
		<!-- /caption-popup -->
		<div class="design">
		 <!-- start content_slider -->
				<div class="wmuSlider example1" id="item">
				   <div class="wmuSliderWrapper">
					   <article style="position: absolute; width: 100%; opacity: 0;">
							<div class=" item-in">
								<img class="dotted"  src="images/qu.png" alt="" >									  
								<p>Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry.</p>
								<span>-Charles Eames</span>
							</div>	
					 	</article>
						<article style="position: absolute; width: 100%; opacity: 0;">
							
								<div class=" item-in">
								<img class="dotted" src="images/qu.png" alt="" >	
								<p>Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry.</p>
								<span>-Charles Eames</span>
							</div>						
					 	</article>
						<article style="position: absolute; width: 100%; opacity: 0;">
							<div class=" item-in">
								<img class="dotted" src="images/qu.png" alt="" >	
								<p>Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry.</p>
								<span>-Charles Eames</span>
							</div>		
					 	</article>					 	
					 </div>
	                <ul class="wmuSliderPagination">
	                	<li><a href="#" class="">0</a></li>
	                	<li><a href="#" class="">1</a></li>
	                	<li><a href="#" class="">2</a></li>
	                </ul>
	            </div>
	            <script src="js/jquery.wmuSlider.js"></script> 
				  <script>
	       			$('.example1').wmuSlider(
						
					);         
	   		     </script> 	           	         						     
			</div>
			<!---->
			<div class="blog" id="blog">
			<h3>BLOG</h3>
				<div class="container">
				<div class=" blog-grids">
					<div class="col-md-3 blog-grid">
						<a href="blog.html"><img class="img-responsive" src="images/b1.jpg" alt="" /></a>
						<div class="blog-top">
							<p>Lorem Ipsum passages, and more recently with desktop publishing software like.</p>
							<a href="#" class="read">READ MORE</a>
							<div class="clearfix"> </div>
						</div>
						<div class="agency">
							<div class="agency-left">
								<h6>Startup Agency</h6>
								<span>Dec 04,2014</span>
							</div>
							<div class="agency-right">
								<ul class="social">
								<li><span> </span>23</li>
								<li><span class="text"> </span>11</li>
								</ul>
							</div>
							<div class="clearfix"> </div>
						</div>
					</div>
					<div class="col-md-3 blog-grid">
						<a href="blog.html"><img class="img-responsive" src="images/b2.jpg" alt="" /></a>
						<div class="blog-top">
							<p>Lorem Ipsum passages, and more recently with desktop publishing software like.</p>
							<a href="#" class="read">READ MORE</a>
							<div class="clearfix"> </div>
						</div>
						<div class="agency">
							<div class="agency-left">
								<h6>Fantasticherie Art</h6>
								<span>Dec 06,2014</span>
							</div>
							<div class="agency-right">
								<ul class="social">
								<li><span> </span>23</li>
								<li><span class="text"> </span>11</li>
								</ul>
							</div>
							<div class="clearfix"> </div>
						</div>
					</div>
					<div class="col-md-3 blog-grid">
						<a href="blog.html"><img class="img-responsive" src="images/b3.jpg" alt="" /></a>
						<div class="blog-top">
							<p>Lorem Ipsum passages, and more recently with desktop publishing software like.</p>
							<a href="#" class="read">READ MORE</a>
							<div class="clearfix"> </div>
						</div>
						<div class="agency">
							<div class="agency-left">
								<h6>Portfolio Review</h6>
								<span>Dec 12,2014</span>
							</div>
							<div class="agency-right">
								<ul class="social">
								<li><span> </span>23</li>
								<li><span class="text"> </span>11</li>
								</ul>
							</div>
							<div class="clearfix"> </div>
						</div>
					</div>
					<div class="col-md-3 blog-grid blog-in">
						<a href="blog.html"><img class="img-responsive" src="images/b4.jpg" alt="" /></a>
						<div class="blog-top">
							<p>Lorem Ipsum passages, and more recently with desktop publishing software like.</p>
							<a href="#" class="read">READ MORE</a>
							<div class="clearfix"> </div>
						</div>
						<div class="agency">
							<div class="agency-left">
								<h6>Genuine Kudos</h6>
								<span>Dec 02,2014</span>
							</div>
							<div class="agency-right">
								<ul class="social">
								<li><span> </span>23</li>
								<li><span class="text"> </span>11</li>
								</ul>
							</div>
							<div class="clearfix"> </div>
						</div>
					</div>
					<div class="clearfix"> </div>
				</div>
				<i class="flower"> </i>
				<a href="#contact" class="scroll blog-down"><img src="images/down.png" alt=""></a>
				</div>
			</div>
			<!---->
			<div class="contact" id="contact">
				<div class="container">
				<h3>CONTACT</h3>
				<P>Start your project today <span>or</span> Maybe you're here just to say '<span>Hi</span>'?</p>
				<form>
					<div class="contact-grid">
						<div class="col-md-6 contact-us">
							<input type="text" value="Name*" onfocus="this.value='';" onblur="if (this.value == '') {this.value = 'Name*';}">
						</div>
						<div class="col-md-6 contact-us">
							<input type="text" value="Email*" onfocus="this.value='';" onblur="if (this.value == '') {this.value = 'Email*';}">
						</div>
						<div class="clearfix"> </div>
					</div>
					<textarea cols="77" rows="6" value=" " onfocus="this.value='';" onblur="if (this.value == '') {this.value = 'Message*';}">Message*</textarea>
					<div class="send ">
						<input type="submit" value="SEND NOW" >
					</div>
				</form>

				</div>
			</div>
	</div>
	<!--footer-->
	<div class="footer">
		<div class="container">
					<a href="#home" class="scroll footer-top"> <img src="images/drop.png" alt=""/></a>

					 <ul class="social-ic-icons">
						<li class="message"><a href="#"><span> </span></a></li>
						<li class="twitter"><a href="#"><span> </span></a></li>
						<li class="dribble"><a href="#"><span> </span></a></li>
						<li class="linked"><a href="#"><span> </span></a></li>
						<li class="behance"><a href="#"><span> </span></a></li>
					</ul>
					<b class="yellow"> </b>
			<p class="footer-grid">Copyright &copy; 2015.Company name All rights reserved.<a target="_blank" href="http://www.777moban.com/">777模板</a></p>
			 
		 </div>
		 <script type="text/javascript">
						$(document).ready(function() {
							/*
							var defaults = {
					  			containerID: 'toTop', // fading element id
								containerHoverID: 'toTopHover', // fading element hover id
								scrollSpeed: 1200,
								easingType: 'linear' 
					 		};
							*/
							
							$().UItoTop({ easingType: 'easeOutQuart' });
							
						});
					</script>			
	 </div>


</body>
</html>