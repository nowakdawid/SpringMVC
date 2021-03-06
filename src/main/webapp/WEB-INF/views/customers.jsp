<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html;charset=ISO-8859-1">
  <link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">
  <title>Customers</title>
</head>
<body>
<section>
  <div class="jumbotron">
    <div class="container">
      <h1>Customers</h1>

      <p>List of all our favourite customers</p>
    </div>
  </div>
</section>
<section class="container">
  <div class="row">
    <c:forEach items="${customers}" var="customer">
      <div class="col-sm-6 col-md-3" style="padding-bottom:15px">
        <div class="thumbnail">
          <div class="caption">
            <p>ID: ${customer.customerId}</p>

            <h3>Name: ${customer.name}</h3>

            <p>Address: ${customer.address}</p>

            <p>Total orders: ${customer.noOfOrdersMade}</p>
          </div>
        </div>
      </div>
    </c:forEach>
  </div>
</section>
</body>
</html>