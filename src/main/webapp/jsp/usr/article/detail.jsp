<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../common/head.jspf"%>

<script>
function Article__loadChatting(Article__Id) {
    console.log(Article__Id);
    fetch(`/usr/article/getArticles/free?fromId=\${Article__Id}&toId=\${Article__Id}`)
        .then(data => data.json())
        .then(responseData => {
            console.log(responseData);
            const article = responseData.data[0];
            const chattingList = article.chattingList;
            const html = chattingList.join('<br>');

            $('.chattings').replaceWith(html);
            setTimeout(Article__loadChatting, 3000, Article__Id);
        });
}

function chatting__submitForm(form) {
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
    <div class="container px-3 mx-auto h-3/5 border-2 bg-stone-400 text-black">
        <h1 class="font-bold text-lg mt-2">채팅창</h1>

        <div class="chattings mt-2">
            <!-- 이 부분에 자바스크립트를 통해서 HTML을 채우겠습니다! -->
        </div>

        <form method="POST" onsubmit="chatting__submitForm(this); return false;" class="mt-2">
            <div class="flex gap-3">
                <div>
                    <input name="chat" type="text" maxlength="300" placeholder="채팅을 입력해주세요." />
                </div>
            </div>

            <div>
                <div>
                    <input class="hover:underline hover:text-[red] cursor-pointer" type="submit" value="전송" />
                </div>
            </div>
        </form>
    </div>
</section>

<script>
Article__loadChatting(${article.id});
</script>

<%@ include file="../common/foot.jspf"%>