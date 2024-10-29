<%@ page import="java.util.List" %>
<%@ page import="model.Participant" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>List of Participants</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
    <div class="container mt-5">
        <h2>List of Participants</h2>
        <table class="table table-striped table-bordered">
            <thead>
                <tr>
                    <th>Participant ID</th>
                    <th>Name</th>
                    <th>Age</th>
                    <th>Batch ID</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
             <% 
    @SuppressWarnings("unchecked")
    List<Participant> participants = (List<Participant>) request.getAttribute("participants");
    if (participants != null) {
        for (Participant participant : participants) { 
%>
                    <tr>
                        <td><%= participant.getId() %></td>
                        <td><%= participant.getName() %></td>
                        <td><%= participant.getAge() %></td>
                        <td><%= participant.getBatchId() %></td>
                        <td>
                            <form action="ParticipantServlet" method="post" style="display:inline;">
                                <input type="hidden" name="_method" value="DELETE">
                                <input type="hidden" name="id" value="<%= participant.getId() %>">
                                <button type="submit" class="btn btn-danger btn-sm">Delete</button>
                            </form>
                        </td>
                    </tr>
                <% 
                        }
                    } else { 
                %>
                    <tr>
                        <td colspan="5" class="text-center">No participants found.</td>
                    </tr>
                <% } %>
            </tbody>
        </table>
        <a href="index.html" class="btn btn-secondary">Back</a>
    </div>
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
