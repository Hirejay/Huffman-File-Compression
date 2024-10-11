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
        <title>Decompressor</title>
        <%@include file="css.jsp" %>
        <style>
            body{
                padding-top: 100px !important;
                background: url('bgforfile.webp') no-repeat center center fixed;
                background-size: cover;
                font-style: italic !important;
            }
        </style>
            
    </head>
    <body>
        <div class="container d-flex justify-content-center">
            <div class="card p-4">
                <h2 class="card-title text-center mb-4">File Decompressor</h2>
                <form action="decompressorservlet" method="post" enctype="multipart/form-data">
                    <div class="form-group">
                        <label for="fileInput">Choose a file:</label>
                        <input type="file" class="form-control-file" id="fileInput" name="file" required />
                    </div>
                    <button type="submit" class="btn btn-primary btn-block">Decompress and Download</button>
                </form>
            </div>
        </div>
    </body>
</html>
