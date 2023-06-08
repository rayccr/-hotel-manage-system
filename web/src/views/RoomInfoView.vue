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
      {{ errorMessage }}
    </div>
    <div v-else style="color: red; font-size: 35px;">
      {{ errorMessage }}
    </div>

    <button @click="roomSet" v-if="$store.state.user.username === 'ray'" type="button" class="btn btn-warning" data-bs-toggle="modal" data-bs-target="#staticBackdrop111" 
        style="width: 400px; margin-top: 15px;">
        修改房间信息
    </button>

    <!-- Modal -->
    <div class="modal fade" id="staticBackdrop111" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
    <div class="modal-dialog modal-xl">
        <div class="modal-content">
        <div class="modal-header">
            <h1 class="modal-title fs-5" id="staticBackdropLabel">修改信息</h1>
            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
        </div>
        <div class="modal-body">


            <div class="row">
              <div class="col-3">
                <div class="input-group mb-3">
                  <span class="input-group-text" id="inputGroup-sizing-default">地点</span>
                  <input v-model="newLocation" id="room-location" type="text" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default">
                </div>
              </div>
              <div class="col-3">
                <div class="input-group mb-3">
                  <span class="input-group-text" id="inputGroup-sizing-default">房型</span>
                  <input v-model="newType" id="room-type" type="text" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default">
                </div>
              </div>
              <div class="col-3">
                <div class="input-group mb-3">
                  <span class="input-group-text" id="inputGroup-sizing-default">价格</span>
                  <input v-model="newPrice" id="room-price" type="text" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default">
                </div>
              </div>
              <div class="col-3">
                <div class="input-group mb-3">
                  <span class="input-group-text" id="inputGroup-sizing-default">数量</span>
                  <input v-model="newCount" id="room-count" type="text" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default">
                </div>
              </div>
            </div>

            <div class="row">
              <div class="col-3">
                <div class="input-group mb-3">
                  <span class="input-group-text" id="inputGroup-sizing-default">折扣</span>
                  <input v-model="newDiscount" id="room-discount" type="text" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default">
                </div>
              </div>
              <div class="col-6">
                <div class="input-group mb-3">
                  <span class="input-group-text" id="inputGroup-sizing-default">介绍</span>
                  <input v-model="newInfo" id="room-info" type="text" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default">
                </div>
              </div>
            </div>

        </div>
        <div class="modal-footer">
            <button @click="clearErrorMessage" type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
            <button @click="roomSetSubmit" type="button" class="btn btn-primary">提交</button>
            <div v-if="errorMessage === 'success'" style="color: green;">{{errorMessage}}</div>
            <div v-else style="color: red;">{{errorMessage}}</div>
        </div>
        </div>
    </div>
    </div>

  </ContentBase>

</template>
  
<script>
import ContentBase from '../components/ContentBase.vue'
import $ from "jquery"
import { ref } from "vue"
import { useStore } from 'vuex'
import { useRoute } from 'vue-router';
// import { Modal } from 'bootstrap/dist/js/bootstrap';
  
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

    let newLocation = ref('');
    let newType = ref('');
    let newPrice = ref('');
    let newCount = ref('');
    let newDiscount = ref('');
    let newInfo = ref('');

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
              errorMessage.value = resp.error_message;
          }
      }); 
      count.value = "";
      days.value = "";
    }


    const roomSet = () => {
      document.getElementById('room-location').value = theRoom.value.location;
      document.getElementById('room-type').value = theRoom.value.type;
      document.getElementById('room-price').value = theRoom.value.price;
      document.getElementById('room-count').value = theRoom.value.count;
      document.getElementById('room-discount').value = theRoom.value.discount;
      document.getElementById('room-info').value = theRoom.value.info;

      newLocation.value = theRoom.value.location;
      newType.value = theRoom.value.type;
      newPrice.value = theRoom.value.price;
      newCount.value = theRoom.value.count;
      newDiscount.value = theRoom.value.discount;
      newInfo.value = theRoom.value.info;
    }

    const roomSetSubmit = () => {
        if(newLocation.value === ''){
          errorMessage.value = "位置不能为空";
          return;
        }if(newType.value === ''){
          errorMessage.value = "类型不能为空";
          return;
        }if(newPrice.value === ''){
          errorMessage.value = "价格不能为空";
          return;
        }if(newCount.value === ''){
          errorMessage.value = "数量不能为空";
          return;
        }if(newDiscount.value === ''){
          errorMessage.value = "折扣不能为空";
          return;
        }if(newInfo.value === ''){
          errorMessage.value = "介绍信息不能为空";
          return;
        }

        $.ajax({
            url: "http://127.0.0.1:5000/api/room/roomset/",
            type: "post",
            data: {
                id: roomId,
                location: newLocation.value,
                type: newType.value,
                price: newPrice.value,
                count: newCount.value,
                discount: newDiscount.value,
                info: newInfo.value,
            },
            headers: {
                'Authorization': "Bearer " + store.state.user.token,
            },
            success(resp) {
                errorMessage.value = resp.error_message;
            }
        }); 

    }

    const clearErrorMessage = () =>{
        errorMessage.value = '';
    }

    return {
        theRoom,
        roomBook, 
        count,
        days,
        errorMessage,
        roomSet,
        roomSetSubmit,
        newLocation,
        newType,
        newPrice,
        newCount,
        newDiscount,
        newInfo,
        clearErrorMessage,
    }

}
}
</script>

<style scoped>
</style>
