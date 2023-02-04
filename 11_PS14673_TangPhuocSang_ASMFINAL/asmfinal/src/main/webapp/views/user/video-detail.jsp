<%@ page pageEncoding="utf-8"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>

<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>${video.title}</title>
<%@ include file="/common/head.jsp"%>

</head>
<body>

	<%@ include file="/common/header.jsp"%>


	<div class="container-fluid tm-container-content tm-mt-60">
		<div class="row mb-4">
			<h2 class="col-12 tm-text-primary">${video.title}</h2>
		</div>
		<div class="row tm-mb-90">
			<div class="col-xl-8 col-lg-7 col-md-6 col-sm-12">
				<iframe id=id=
					"tm-video" src="https://www.youtube.com/embed/${video.href}" style="width:900px;height:500px;"></iframe>

			</div>
			<div class="col-xl-4 col-lg-5 col-md-6 col-sm-12">
				<div class="tm-bg-gray tm-video-details">
					<c:if test="${not empty sessionScope.currentUser}">
						<div class="text-center mb-5">
							<a href="#" class="btn btn-primary tm-btn-big">Share</a>
						</div>
						<div class="text-center mb-5">
							<button id="likeOrUnlikeBtn" class="btn btn-primary tm-btn-big">
								<c:choose>
									<c:when test="${flagLikedBtn == true}">
									Unlike</c:when>
									<c:otherwise>Like</c:otherwise>
								</c:choose>
							</button>
						</div>
					</c:if>
					<p class="mb-4">${video.description}</p>
					<div class="mb-4">
						<h3 class="tm-text-gray-dark mb-3">Description</h3>
						<p>${video.description}</p>
					</div>
					<input id="videoIdHdn" type="hidden" value="${video.href}">
					<div>
						<h3 class="tm-text-gray-dark mb-3">Tags</h3>
						<a href="#" class="tm-text-primary mr-4 mb-2 d-inline-block">Cloud</a>
						<a href="#" class="tm-text-primary mr-4 mb-2 d-inline-block">Bluesky</a>
						<a href="#" class="tm-text-primary mr-4 mb-2 d-inline-block">Nature</a>
						<a href="#" class="tm-text-primary mr-4 mb-2 d-inline-block">Background</a>
						<a href="#" class="tm-text-primary mr-4 mb-2 d-inline-block">Timelapse</a>
						<a href="#" class="tm-text-primary mr-4 mb-2 d-inline-block">Night</a>
						<a href="#" class="tm-text-primary mr-4 mb-2 d-inline-block">Real
							Estate</a>
					</div>
				</div>
			</div>
		</div>
		<!-- row -->
	</div>
	<!-- container-fluid, tm-container-content -->
	<%@ include file="/common/footer.jsp"%>
	<script>
		$('#likeOrUnlikeBtn').click(function() {
			var videoId = $('#videoIdHdn').val();
			$.ajax({
				url: 'video?action=like&id='+ videoId
			}).then(function(data){
				var text = $('#likeOrUnlikeBtn').text();
				if(text.indexOf('Like') != -1) {
					$('#likeOrUnlikeBtn').text('Unlike');
				} else {
					$('#likeOrUnlikeBtn').text('Like');
				}
			}).fail(function(error){
				alert('Please try again ^^');
			});
		});
	</script>

</body>
</html>