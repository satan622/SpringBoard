<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@include file="../include/header.jsp"%>

<section class="content">
	<div class="row">
		<div class="col-md-12">
			<div class="box box-primary">
				<div class="box-header">
					<h3 class="box-title">READ BOARD</h3>
				</div>
				<form role="form" action="modifyPage" method="post">
					<input type="hidden" name="page" value="${cri.page }">
					<input type="hidden" name="perPageNum" value="${cri.perPageNum }">
					
					<div class="box-body">
						<div class="form-group">
							<label for="exampleInputEmail1">BNO</label> <input type="text"
								name="bno" class="form-control" value="${boardVO.bno }"
								readonly="readonly">
						</div>
						<div class="form-group">
							<label for="exampleInputEmail1">Title</label> <input type="text"
								name="title" class="form-control" value="${boardVO.title }">
						</div>
						<div class="form-group">
							<label for="exampleInputPassword1">Content</label>
							<textarea class="form-control" name="content" rows="3">${boardVO.content }</textarea>
						</div>
						<div class="form-group">
							<label for="exampleInputEmail1">writer</label> <input type="text"
								name="writer" class="form-control" value="${boardVO.writer }">
						</div>
					</div>
				</form>
				<div class="box-footer">
					<button type="submit" class="btn btn-primary">SAVE</button>
					<button type="submit" class="btn btn-warning">CANCEL</button>
				</div>
				<script type="text/javascript">
					$(document).ready(function() {
						var formObj = $("form[role='form']");
						console.log(formObj);
						//"CANCEL"을 눌렀을때 그냥 listAll이동
						//이동시: page, perPageNum
						$(".btn-warning").on("click", function() {
							self.location = "/board/listPage?page=${cri.page}&perPageNum=${cri.perPageNum}";
						});
						//"SAVE"를 눌렀을때 form role=form post를 통해서 전송
						//이동시: page, perPageNum
						$(".btn-primary").on("click", function() {
							formObj.submit();
						});
					});
				</script>
			</div>
		</div>
	</div>
</section>

<%@include file="../include/footer.jsp"%>
