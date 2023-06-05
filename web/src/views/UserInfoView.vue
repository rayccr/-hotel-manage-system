<template>
    <ContentBase>
        <h1>
            消费记录
        </h1>

    </ContentBase>

</template>

<script>
import ContentBase from '@/components/ContentBase.vue';

export default{
    name: "UserInfoView",
    components:{
        ContentBase,
    },

}

</script>

<style scoped>


.username {
    font-size:32px;
    font-weight: bold;
}

.fans {
    font-size: 25px;
    color: gray;
    margin-top: 30px;
}

button {
    padding: 5px 5px;
    
}

</style>