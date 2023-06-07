<template>
    <ContentBase>
        <div>消费记录</div>
    </ContentBase>
</template>

<script>
import ContentBase from "../components/ContentBase.vue";
import { useStore } from "vuex";


export default{
    name: "RoomBookView",
    components:{
        ContentBase,
    },

    setup(){

        const store = useStore();
        

        return {
            store,
        }
    }

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