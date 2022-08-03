<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../common/head.jspf"%>

<script>
function Article__loadReply(Article__Id) {
    fetch(`/usr/article/getReplys/free?id=\${Article__Id}`)
        .then(data => data.json())
        .then(responseData => {
            const replyList = responseData.data.replyList;
            const html = replyList.join('<br>');

            $('.replys').replaceWith(html);
            setTimeout(Article__loadReply, 3000, Article__Id);
        });
}

function reply__submitForm(form) {
    form.body.value = form.body.value.trim();

    if ( form.body.value.length == 0 ) {
        alert('내용을 입력해주세요.');
        form.body.focus();
        return;
    }

    form.submit();
}
</script>

<section>
    <div class="container px-3 mx-auto">
        <h1 class="font-bold text-lg">게시물 상세페이지</h1>

        <div>
            <div>
                ID : ${article.id}
            </div>
            <div>
                TITLE : ${article.title}
            </div>
            <div>
                BODY : ${article.body}
            </div>
        </div>
    </div>
</section>
<br>
<section class="h-screen">
    <div class="container px-3 mx-auto h-3/5 border-2 mt-2">
        <h1 class="font-bold text-lg mt-2">댓글</h1>

        <div class="replys">
            <!-- 이 부분에 자바스크립트를 통해서 HTML을 채우겠습니다! -->
        </div>

        <form method="POST" onsubmit="reply__submitForm(this); return false;">
            <input autofocus name="reply" type="text" placeholder="댓글을 입력해주세요." class="input input-bordered" />
            <input type="submit" value="작성" class="btn btn-outline btn-primary"/>
        </form>
    </div>
</section>

<script>
Article__loadReply(${article.id});
</script>

<%@ include file="../common/foot.jspf"%>