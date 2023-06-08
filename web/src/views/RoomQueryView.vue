<template>

<ContentBase>
  <div class="container text-center">
    <div class="row">
      <div class="col-3">
        <ContentBase style="margin-bottom: 15px;">
            查询条件
        </ContentBase>

        <div class="input-group mb-3">
          <span class="input-group-text" id="inputGroup-sizing-default">房型</span>
          <input v-model="roomType" type="text" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default">
        </div>
        <div class="input-group mb-3">
          <span class="input-group-text" id="inputGroup-sizing-default">位置</span>
          <input v-model="roomLocation" type="text" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default">
        </div>
        <div class="input-group">
          <span class="input-group-text">最低价/最高价</span>
          <input v-model="roomLowerPrice" type="text" aria-label="First name" class="form-control">
          <input v-model="roomHigherPrice" type="text" aria-label="Last name" class="form-control">
        </div>

        <div style="margin-top: 15px;">
          <button @click="roomQuery" type="button" class="btn btn-primary" style="width: 200px;">查询</button>
        </div>


      </div>
      <div class="col-9">
        <ContentBase>
          查询结果
        </ContentBase>

        <ContentBase v-for="room in rooms.rooms" :key="room.id">
          <div class="row">
            <div class="col-4" v-if="room.count !== 0" style="color: green;">
              状态: 可租用
            </div>
            <div class="col-4" v-else style="color: red;">
              状态: 无可租用数量
            </div>
            <div class="col-4">
              地点：{{ room.location }}
            </div>
            <div class="col-4">
              房型：{{ room.type }}
            </div>
          </div>

          <div class="row">
            <div class="col-4">
              价格：{{ room.price }}
            </div>
            <div class="col-4">
              数量：{{ room.count }}
            </div>
            <div class="col-4">
              折扣：{{ room.discount }}
            </div>
            <div class="col-4">
              房间编号：{{ room.id }}
            </div>
          </div>

          <div>
            介绍：{{ room.info }}
          </div>
  
          <button @click="open_room_info(room.id)" type="button" class="btn btn-success">查看详细</button>
        </ContentBase>

      </div>
    </div>
  </div>
</ContentBase>


</template>

<script>
import ContentBase from '../components/ContentBase'
import { useStore } from 'vuex';
import { ref } from "vue"
import $ from "jquery"
import router from '@/router/index';

export default {
  name: 'RoomQueryView',
  components: {
      ContentBase,
  },

  setup(){

    const store = useStore();
    let roomType = ref('');
    let roomLocation = ref('');
    let roomLowerPrice = ref('');
    let roomHigherPrice = ref('');
    let rooms = ref([]);

    const roomQuery = () =>{

      if(roomType.value === ''){
        roomType.value = -1;
      }if(roomLocation.value === ''){
        roomLocation.value = -1;
      }if(roomLowerPrice.value === ''){
        roomLowerPrice.value = -1;
      }if(roomHigherPrice.value === ''){
        roomHigherPrice.value = -1;
      }

      $.ajax({
        url: "http://127.0.0.1:5000/api/room/roomquery/",
        type: "post",
        data: {
            type: roomType.value,
            location: roomLocation.value,
            lowerPrice: roomLowerPrice.value,
            highPrice: roomHigherPrice.value,
          },
        headers: {
            'Authorization': "Bearer " + store.state.user.token,
        },
        success(resp) {
            console.log(resp);
            rooms.value = resp;
        }
      });

      roomType.value = "";
      roomLocation.value = "";
      roomLowerPrice.value = "";
      roomHigherPrice.value = "";
    }

    const open_room_info = (roomId) =>{
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
                rooms.value = resp; // resp 里面是room
            }
        }); 

          router.push({ // 跳转具体页面
            name: "roominfo",
            params: {
              roomId,
            }
          })

        }



    return {
      roomType,
      roomLocation,
      roomLowerPrice,
      roomHigherPrice,
      roomQuery,
      rooms,
      open_room_info,
    }

  }


}
</script>

<style scoped>

</style>
