<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@include file="../include/header.jsp"%>

<style>
.fileDrop {
	width: 80%;
	height: 100px;
	border: 1px dotted gray;
	background-color: lightslategrey;
	margin: auto;
}
</style>

<section class="content">
	<div class="row">
		<div class="col-md-12">
			<div class="box box-primary">
				<div class="box-header">
					<h3 class="box-title">REGISTER BOARD</h3>
				</div>
				<form id="registerForm" role="form" method="post">
					<div class="box-body">
						<div class="form-group">
							<label for="exampleInputEmail1">Title</label> <input type="text"
								name="title" class="form-control" placeholder="Enter Title">
						</div>
						<div class="form-group">
							<label for="exampleInputPassword1">Content</label>
							<textarea class="form-control" name="content" rows="3"
								placeholder="Enter ..."></textarea>
						</div>


						<div class="form-group">
							<label for="exampleInputEmail1">writer</label> <input type="text"
								name="writer" class="form-control" value='${login.uid}' readonly>
						</div>
						<div class="form-group">
							<label for="exampleInputEmail1">File DROP Here</label>
							<div class="fileDrop"></div>
						</div>
					</div>


					<div class="box-footer">
						<div>
							<hr>
						</div>

						<ul class="mailbox-attachments clearfix uploadedList">
						</ul>
						<button type="submit" class="btn btn-primary">Submit</button>
					</div>
				</form>
			</div>
		</div>
	</div>
</section>

<script type="text/javascript" src="/resources/js/upload.js"></script>

<script
	src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/4.0.11/handlebars.js"></script>


<script id="template" type="text/x-handlebars-template">
<li>
  <span class="mailbox-attachment-icon has-img"><img src="{{imgsrc}}" alt="Attachment"></span>
  <div class="mailbox-attachment-info">
	<a href="{{getLink}}" class="mailbox-attachment-name">{{fileName}}</a>
	<a href="{{fullName}}" class="btn btn-default btn-xs pull-right delbtn">
		<i class="fa fa-fw fa-remove"></i>
	</a>
	</span>
  </div>
</li>  
</script>

<script>
	var template = Handlebars.compile($("#template").html());

	$(".fileDrop").on("dragenter dragover", function(event) {
		event.preventDefault(); //화면 변화x
	});

	$(".fileDrop").on("drop", function(event) {
		event.preventDefault(); //화면 변화x

		var files = event.originalEvent.dataTransfer.files; //전달된 파일 데이터를 가져올 때
		var file = files[0];

		var formData = new FormData(); //FormData를 이용해서 서버로 파일전송 호출
		formData.append("file", file); //'file' 이름으로 끌어다 놓은 파일 객체를 추가

		$.ajax({
			url : '/uploadAjax',
			data : formData,
			dataType : 'text',
			processData : false,
			contentType : false,
			type : 'POST',
			success : function(data) {
				var fileInfo = getFileInfo(data);
				var html = template(fileInfo);

				$(".uploadedList").append(html);
			}
		});
	});

	$("#registerForm").submit(
			function(event) {
				event.preventDefault();
				var that = $(this);
				var str = "";
				$(".uploadedList .delbtn").each(
						function(index) {
							str += "<input type='hidden' name='files[" + index
									+ "]' value='" + $(this).attr("href")
									+ "'>";
						});

				that.append(str);
				that.get(0).submit();
			});

	$(".uploadedList").on("click", ".delbtn", function(event) {
		event.preventDefault();

		var that = $(this);

		//alert("DELETE FILE");

		$.ajax({
			url : "/deleteFile",
			type : "post",
			data : {
				fileName : $(this).attr("href")
			},
			dataType : "text",
			success : function(result) {
				console.log("RESULT: " + result);
				if (result == 'deleted') {
					that.closest("li").remove();
				}
			}
		});
	});
</script>

<%@include file="../include/footer.jsp"%>
