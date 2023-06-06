<template>
  <ContentBase>
        <div>房间信息</div>  
  </ContentBase>
  
  <ContentBase>
    <div class="row">
      <div class="col-4">
        地点：{{ theRoom.location }}
      </div>
      <div class="col-4">
        房型：{{ theRoom.type }}
      </div>
      <div class="col-4">
        价格：{{ theRoom.price }}
      </div>
    </div>
  
    <div class="row">
      <div class="col-4">  
        数量：{{ theRoom.count }}
      </div>
      <div class="col-4">
        折扣：{{ theRoom.discount }}
      </div>
      <div class="col-4">
        房间编号：{{ theRoom.id }}
      </div>
    </div>
    <div>
      介绍：{{ theRoom.info }}
    </div>

    <div class="input-group mb-3" style="width: 400px; margin-top: 50px;">
        <span class="input-group-text" id="inputGroup-sizing-default">数量</span>
        <input v-model="count" type="text" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default">
    </div>
    <div class="input-group mb-3" style="width: 400px;">
        <span class="input-group-text" id="inputGroup-sizing-default">天数</span>
        <input v-model="days" type="text" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default">
    </div>

    <button @click="roomBook" type="button" class="btn btn-success" style="width: 400px">订房</button>
    <span style="color: red;"> 注意这是当天入住</span>

    <div v-if="errorMessage === 'success'" style="color: green; font-size: 35px;">
      订房成功
    </div>
    <div v-else style="color: red; font-size: 35px;">
      {{ errorMessage }}
    </div>
  </ContentBase>


  </template>
  
<script>
import ContentBase from '../components/ContentBase.vue'
import $ from "jquery"
import { ref } from "vue"
import { useStore } from 'vuex'
import { useRoute } from 'vue-router';
  
export default {
name: 'RoomInfoView',
components: {
    ContentBase,
},

setup(){
    const route = useRoute();
    const roomId = parseInt(route.params.roomId);
    const store = useStore();

    let count = ref('');
    let days = ref('');
    let errorMessage = ref('');
    let theRoom = ref('');

    $.ajax({
        url: "http://127.0.0.1:5000/api/room/roomqueryone/",
        type: "post",
        data: {
            id: roomId,
        },
        headers: {
            'Authorization': "Bearer " + store.state.user.token,
        },
        success(resp) {
            theRoom.value = resp.room; // resp 里面是room
        }
    }); 


    const roomBook = () =>{
      errorMessage.value = "";

      if(days.value === ''){
        errorMessage.value = "天数不能为空";
        return;
      }if(count.value === ''){
        errorMessage.value = "数量不能为空";
        return;
      }

      $.ajax({
          url: "http://127.0.0.1:5000/api/room/roombook/",
          type: "post",
          data: {
              roomId: roomId,
              userId: store.state.user.id, 
              count: count.value,
              days: days.value,
          },
          headers: {
              'Authorization': "Bearer " + store.state.user.token,
          },
          success(resp) {
              console.log(resp.error_message);
              errorMessage.value = resp.error_message;
          }
      }); 
      
      count.value = "";
      days.value = "";
    }

    return {
        theRoom,
        roomBook, 
        count,
        days,
        errorMessage,
    }

}
}
</script>

<style scoped>
</style>
