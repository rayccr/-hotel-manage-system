<template>

    <ContentBase>
      <form class="row g-3">
        <div class="col-md-4">
          <label for="inputCity" class="form-label">房间类型</label>
          <input v-model="roomType" type="text" class="form-control" id="inputCity">
        </div>
        <div class="col-md-4">
          <label for="inputCity" class="form-label">价格</label>
          <input v-model="roomPrice" type="text" class="form-control" id="inputCity">
        </div>
        <div class="col-md-4">
          <label for="inputCity" class="form-label">数量</label>
          <input v-model="roomCount" type="text" class="form-control" id="inputCity">
        </div>
        <div class="col-md-4">
          <label for="inputCity" class="form-label">折扣情况(0-1)</label>
          <input v-model="roomDiscount" type="text" class="form-control" id="inputCity">
        </div>
        <div class="col-md-4">
          <label for="inputCity" class="form-label">位置</label>
          <input v-model="roomLocation" type="text" class="form-control" id="inputCity">
        </div>
        <div class="col-md-12">
          <label for="inputCity" class="form-label">介绍</label>
          <input v-model="roomInfo" type="text" class="form-control" id="inputCity">
        </div>
        <div class="col-12">
          <!-- Button trigger modal -->
          <button @click="add_one_room" type="button" class="btn btn-success" 
          data-bs-toggle="modal" data-bs-target="#exampleModal" style="width: 300px;">
            提交
          </button>
            
        
        </div>
      </form>

      <!-- Modal -->
      <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog">
          <div class="modal-content">
            <div class="modal-header">
              <h1 class="modal-title fs-5" id="exampleModalLabel">操作状态</h1>
              <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>

            <div v-if="flag === 1" class="modal-body" style="color: darkgreen;">
              <span class="error-message">
                {{ res }}
              </span>
            </div>
            
            <div v-else class="modal-body" style="color: red;">
              <span class="error-message">
                {{ res }}
              </span>
            </div>
            
            <div class="modal-footer">
              <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">关闭</button>
            </div>
          </div>
        </div>
      </div>

    </ContentBase>

</template>
  
<script>
  import ContentBase from '../components/ContentBase.vue'
  import $ from "jquery"
  import { useStore } from 'vuex';
  import { ref } from 'vue';

  export default {
    name: 'HomeView',
    components: {
      ContentBase,
    },

    setup(){

      const store = useStore();
      let roomType = ref('');
      let roomPrice = ref('');
      let roomCount = ref('');
      let roomDiscount = ref('');
      let roomLocation = ref('');
      let roomInfo = ref('');
      let flag = ref('');
      let res = ref('');

      const add_one_room = () => {

          flag.value = 0;

          if(roomType.value === ''){
            res.value = "请输入房间类型";
            return;
          }if(roomPrice.value === ''){
            res.value = "请输入房间价格";
            return;
          }if(roomCount.value === ''){
            res.value = "请输入房间数量";
            return;
          }if(roomDiscount.value === ''){
            res.value = "请输入房间折扣";
            return;
          }if(roomLocation.value === ''){
            res.value = "请输入房间位置";
            return;
          }if(roomInfo.value === ''){
            res.value = "请输入房间信息";
            return;
          }
          
          
          flag.value = 1; // 添加成功
          res.value = "添加成功";
          $.ajax({
              url: "http://127.0.0.1:5000/api/user/roomadd/",
              type: "post",
              data: {
                  type: roomType.value,
                  price:roomPrice.value,
                  count: roomCount.value,
                  discount: roomDiscount.value,
                  location: roomLocation.value,
                  info: roomInfo.value,
              },
              headers: {
                  'Authorization': "Bearer " + store.state.user.token,
              },
              success(resp) {
                  console.log(resp);
              }
            });

          roomType.value = '';
          roomPrice.value = '';
          roomCount.value = '';
          roomDiscount.value = '';
          roomLocation.value = '';
          roomInfo.value = '';
      }

      return{
        add_one_room,
        roomType,
        roomPrice,
        roomCount,
        roomDiscount,
        roomLocation,
        roomInfo,
        flag,
        res,
      }
    }

  }
</script>
  
<style scoped>
</style>
  