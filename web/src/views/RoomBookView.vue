<template>
    <ContentBase>
        <div>预定</div>
    </ContentBase>
</template>

<script>
import ContentBase from "../components/ContentBase.vue";

export default{
    name: "ChatView",
    components:{
        ContentBase,
},

}

</script>

<style scoped>
.edit-field{
    position: absolute;
    bottom: 0px;
    width: 100%;
}

.myscroll{
    color: brown;
    overflow:auto;
    height:65vh;
    width: 80vw;
    position:absolute;
}

</style>