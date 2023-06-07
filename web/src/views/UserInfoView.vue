<template>
    <ContentBase>
        <h1>
            消费记录
        </h1>
    </ContentBase>


    <!-- <ContentBase v-for="info in roomBookInfo" :key="info.startDay"> -->
    <ContentBase v-for="(info, index) in (roomBookInfo, roomBookInfo.length)" :key="info.startDay">
        <div class="row">
            <div class="col-3">
                房间编号：{{ roomBookInfo[index].roomId }}
            </div>
            <div class="col-3">
                房间类型: {{ rooms[index].type }}
            </div>
            <div class="col-3">
                房间位置: {{ rooms[index].location }}
            </div>
            <div class="col-3">
                房间信息：{{ rooms[index].info }}
            </div>
        </div>
        <div class="row">
            <div class="col-3">
                订房时间：{{ roomBookInfo[index].startDay }}
            </div>
            <div class="col-3">
                订房数量: {{ roomBookInfo[index].count }}
            </div>
            <div class="col-3">
                订房天数: {{ roomBookInfo[index].days }}
            </div>
        </div>
        <div class="row">
            <div class="col-4">
                订房价格: {{ roomBookInfo[index].cost }}
            </div>
        </div>
    </ContentBase>

</template>

<script>
import ContentBase from '@/components/ContentBase.vue';
import $ from "jquery";
import { useStore } from 'vuex';
import { ref } from 'vue';

export default{
    name: "UserInfoView",
    components:{
        ContentBase,
    },


    setup(){

        const store = useStore();
        let rooms = ref([]);
        let roomBookInfo = ref([]);

        $.ajax({
            url: "http://127.0.0.1:5000/api/user/roombookinfo/",
            type: "post",
            data: {
                userId: store.state.user.id,
            },
            headers: {
                'Authorization': "Bearer " + store.state.user.token,
            },
            success(resp) {
                roomBookInfo.value = resp.roomBookInfo;
                rooms.value = resp.rooms;
                console.log(roomBookInfo.value);
                console.log(rooms.value);
            }
        });

        return{
            rooms,
            roomBookInfo,
        }
    }

}

</script>

<style scoped>
</style>