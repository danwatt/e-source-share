<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<!DOCTYPE html">
<html lang="en">
	<head>
	    <meta charset="utf-8" />
	    <title>eSourceShare</title>
	    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
	    <!--[if lt IE 9]>
	      <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
	    <![endif]-->
	    <link href="resources/bootstrap.min.css" rel="stylesheet" />
	    <script type="text/javascript" src="resources/jquery.min.js"></script>
	    <script type="text/javascript" src="resources/bootsrap.min.js"></script>
	    <script type="text/javascript" src="resources/knockout.min.js"></script>
	    <script type="text/javascript" src="resources/source.js"></script>
    <style>
      body {
        padding-top: 60px; /* 60px to make the container go all the way to the bottom of the topbar */
      }
    </style>
    </head>
    <body>
  <div class="navbar navbar-fixed-top">
      <div class="navbar-inner">
        <div class="container">
          <button type="button" class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="brand" href="#">eSourceShare</a>
          <div class="nav-collapse collapse">
            <ul class="nav">
              <li class="active"><a href="#">Home</a></li>
              <li><a href="#about">About</a></li>
              <li><a href="#contact">Contact</a></li>
            </ul>
          </div><!--/.nav-collapse -->
        </div>
      </div>
    </div>

    <div class="container">
    	<input type="text" class="input-block-level" placeholder="Title" />
		<textarea class="input-block-level" id="source" rows="10" placeholder="Source"></textarea>
		<button class="btn btn-primary pull-right" href="#" id="save">Save</button>
    </div> <!-- /container -->
</body></html>