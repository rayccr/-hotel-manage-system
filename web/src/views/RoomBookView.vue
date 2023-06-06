<template>
    <ContentBase>
        <div>预定</div>
        <div>输入房间号, 预定数量, 预定天数，生成消费单</div>
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