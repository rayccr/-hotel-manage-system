<template>
  <ContentBase>
      <h2>管理员名字:ray;密码:123, 前端判断权限</h2>
</ContentBase>

<ContentBase>
      <div>房间列表</div>  
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



</template>

<script>
import ContentBase from '../components/ContentBase.vue'
import $ from "jquery"
import { ref } from "vue"
import { useStore} from 'vuex'
import router from '@/router/index';

export default {
  name: 'HomeView',
  components: {
    ContentBase,
  },

  setup(){
  
    const store = useStore();
    let rooms = ref([]);
    
    $.ajax({
        url: "http://127.0.0.1:5000/api/room/roomlist/",
        type: "post",
        data: {
        },
        headers: {
            'Authorization': "Bearer " + store.state.user.token,
        },
        success(resp) {
          rooms.value = resp;
        }
      });

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
      rooms,
      open_room_info,
    }
  }

}
</script>

<style scoped>
</style>
