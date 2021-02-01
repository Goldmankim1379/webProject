<%@ page language="java" contentType="text/html; charset=UTF-8"   pageEncoding="UTF-8"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<<script type="text/javascript">

$(document).ready(function() {
	
	var msg = "${msg}";
	
	if(msg != ""){
		alert(msg);	
	}
	
});

</script>


				<%@include file="../includes/header.jsp" %>

                <!-- Begin Page Content -->
                <div class="container-fluid">

                    <!-- Page Heading -->
                    <h1 class="h3 mb-2 text-gray-800">Tables</h1>
                    <p class="mb-4">DataTables is a third party plugin that is used to generate the demo table below.
                        For more information about DataTables, please visit the <a target="_blank"
                            href="https://datatables.net">official DataTables documentation</a>.</p>

                    <!-- DataTales Example -->
                    <div class="card shadow mb-4">
                        <div class="card-header py-3">
                            <h6 class="m-0 font-weight-bold text-primary">DataTables Example</h6>
                        </div>
                        <div class="card-body">
                            <div class="table-responsive">
                                <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0" style="text-align:center;">
                                    <thead>
                                        <tr>
                                            <th>번호</th>
                                            <th>제목</th>
                                            <th>작성자</th>
                                            <th>등록일</th>
                                        </tr>
                                    </thead>
                                    <c:forEach var="list" items="${boardList}">
                                    	<tr>
                                    		<td><c:out value="${list.board_idx}" /></td>
                                    		<td>
                                    		<a href="/board/read?board_idx=${list.board_idx}" >
                                    		<c:out value="${list.board_title}" />
                                    		</a>
                                    		</td>
                                    		<td><c:out value="${list.board_writer}" /></td>
                                    		<td><c:out value="${list.board_regdate}" /></td>
                                    	</tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                                <a href="/board/create" class="btn btn-primary" >등록</a>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- /.container-fluid -->
                
                <%@include file="../includes/footer.jsp" %>