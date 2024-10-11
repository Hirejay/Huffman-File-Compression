<%-- 
    Document   : index
    Created on : 23-Jul-2024, 2:42:43 am
    Author     : Jay Hire
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Welcome To File Compressor</title>
        <%@include file="css.jsp" %>
        <style>
            body{
                padding-top: 100px !important;
                background: url('bgforfile.webp') no-repeat center center fixed;
                background-size: cover;
            }
        </style>
            
    </head>
    <body>
        <div class="container text-center">
            <h1><i>File Compression</i></h1>
            <h6><i>Using</i></h6>
            <h3><i>Huffman Coding</i></h3>
            <div class="container mt-5 ">
                <a  href="compressor.jsp" class="btn btn-outline-primary mr-5">Compress</a>
                
                <a href="decompressor.jsp" class="btn btn-outline-secondary ml-5">Decompress</a>
            </div>
                
            
        </div>
        
    </body>
</html>
