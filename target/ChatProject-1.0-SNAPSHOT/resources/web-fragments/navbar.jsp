<nav class="navbar">
    <a href="/"><img class="logo" src="resources/images/logo.png"></a>

    <ul>
        <li><a href="/">Home</a> </li>
        <li><a href="#">About</a> </li>
        <li><a href="#">Support</a></li>
        <li id="login">
            <c:choose>
                <c:when test="${user==null}">
                    <a href ="/login">Login</a>
                </c:when>
                <c:otherwise>
                    <a href="/logout">Logout</a>
                </c:otherwise>
            </c:choose>
        </li>

    </ul>
</nav>