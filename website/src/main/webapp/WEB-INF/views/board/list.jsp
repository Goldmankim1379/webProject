<%@ page language="java" contentType="text/html; charset=UTF-8"   pageEncoding="UTF-8"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script type="text/javascript">

$(document).ready(function() {
	
	var thisIndex = "${searchVO.pageIndex}"
	$(".pagination li a").each(function(){
		var idx = $(this).parent().index();
		var thistitle = $(this).attr("title");
		if(thistitle == thisIndex){
			$(".pagination").find("li").eq(idx).addClass("active");
		}
	});
	
	
	
	var msg = "${msg}";
	
	if(msg != ""){
		alert(msg);	
	}
	
});

function fn_go_page(pageNo) {
	$("#pageIndex").val(pageNo);
	$("#listForm").submit();
	return false;
}

function fn_search(){
	$("#pageIndex").val("1");
	$("#listForm").submit();
	return false;
}

</script>

				<%@include file="../includes/header.jsp" %>

				<form method="get"  id="listForm" action="/board/list">
				<input type="hidden" id="pageIndex" name="pageIndex" val="" />
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
                        <!-- 검색[s] -->
                        <div class="card-header py-3">
                            <input type="text" id="searchKeyword" name="searchKeyword" value="${searchVO.searchKeyword}" style="width:300px; height:40px;" placeholder="검색어를 입력하세요." />
							<a href="javascript:void(0)" onclick="fn_search(); return false;" class="btn btn-primary">검색</a>
                        </div>
                        <!-- 검색[e] -->
                        <div class="col-sm-12 col-md-5">
                        <div class="dataTables_info" id="dataTable_info" role="status" aria-live="polite">
						<span>총게시물 ${totCnt} / 페이지 (${searchVO.pageIndex} / ${totalPageCnt})</span>
						</div></div>
                        <div class="card-body">
                            <div class="table-responsive">
                                <table class="table table-bordered" width="100%" cellspacing="0" style="text-align:center;">
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
                                    		<a href="/board/read?board_idx=${list.board_idx}&${searchVO.qustr}" >
                                    		<c:out value="${list.board_title}" />
                                    		</a>
                                    		</td>
                                    		<td><c:out value="${list.board_writer}" /></td>
                                    		<td><c:out value="${list.board_regdate}" /></td>
                                    	</tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                                
                                 <!-- Paging[s] -->
                                 
                                 <div class="col-sm-12 col-md-7" style="text-align:right">
	                                <div class="dataTables_paginate paging_simple_numbers" id="dataTable_paginate">
		                                <ul class="pagination">
		                                
		                                <c:if test="${searchVO.prev}">
		                                <li class="paginate_button page-item previous" id="dataTable_previous">
		                               		 <a href="javascript:void(0);" onclick="fn_go_page(${searchVO.startDate - 1}); return false;" aria-controls="dataTable" data-dt-idx="0" tabindex="0" class="page-link">Previous</a>
		                                </li>
		                                </c:if>
		                                
		                                <c:forEach var="num" begin="${searchVO.startDate}" end="${searchVO.endDate}">
		                                <li class="paginate_button page-item">
		                               		 <a href="javascript:void(0);" onclick="fn_go_page(${num}); return false;" aria-controls="dataTable" data-dt-idx="0" tabindex="0" class="page-link" title="${num}">${num}</a>
		                                </li>
		                                </c:forEach>
		                                
		                                <c:if test="${searchVO.next}">
		                                <li class="paginate_button page-item next" id="dataTable_next">
		                               		 <a href="javascript:void(0);" onclick="fn_go_page(${searchVO.endDate + 1}); return false;" aria-controls="dataTable" data-dt-idx="0" tabindex="0" class="page-link">Next</a>
		                                </li>
		                                </c:if>
		                                </ul>
	                                </div>
                                </div>
				                <!-- Paging[e] -->
				                
                                <a href="/board/create" class="btn btn-primary" >등록</a>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- /.container-fluid -->
               	
               	</form>
                
                <%@include file="../includes/footer.jsp" %>