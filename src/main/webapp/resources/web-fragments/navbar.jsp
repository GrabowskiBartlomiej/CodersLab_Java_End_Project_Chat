<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<nav class="navbar">
    <a href="/"><img class="logo" src="resources/images/logo.png"></a>

    <ul>
        <li>
            <c:choose>
                <c:when test="${user==null}">
                    <a href="/">Home</a>
                </c:when>
                <c:otherwise>
                    <a href="/chat/1">Chat</a>
                </c:otherwise>
            </c:choose>
        </li>

        <li><a href="#">About</a></li>
        <li><a href="#">Support</a></li>
        <li id="login">
            <c:choose>
                <c:when test="${user==null}">
                    <a href="/login">Login</a>
                </c:when>
                <c:otherwise>
                    <a href="/logout">Logout</a>
                </c:otherwise>
            </c:choose>
        </li>

    </ul>
</nav>