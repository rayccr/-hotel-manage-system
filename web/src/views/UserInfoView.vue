<template>
    <ContentBase>
        <h3>
            消费记录 and 换房续房
        </h3>
    </ContentBase>

    <!-- <ContentBase v-for="(info, index) in (roomBookInfo, roomBookInfo.length)" :key="index"> -->
    <ContentBase v-for="(info, index) in roomBookInfo" :key="index">
        <div class="row">
            <div class="col-3">
                房间编号：{{ info.roomId }}
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
                订房时间：{{ info.startDay }}
            </div>
            <div class="col-3">
                订房数量: {{ info.count }}
            </div>
            <div class="col-3">
                订房天数: {{ info.days }}
            </div>
            <div class="col-3">
                <span v-if="info.state === false" style="color: red;">
                    支付状态: 未支付
                </span>
                <span v-else style="color: green;">
                    支付状态: 已支付
                </span>
            </div>
        </div>
        <div class="row">
            <div class="col-4">
                订房价格: {{ info.cost }}
            </div>
        </div>



        <!-- 超级大坑模态框内部接收不了vue的遍历属性!!!!!!! -->
        <button @click="updateCurRoomId(info.roomId)" v-if="info.state === false" type="button" class="btn btn-warning" data-bs-toggle="modal" data-bs-target="#staticBackdrop111" 
            style="width: 200px; margin-top: 15px;">
            续房
        </button>

        <!-- Modal -->
        <div class="modal fade" id="staticBackdrop111" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
            <div class="modal-header">
                <h1 class="modal-title fs-5" id="staticBackdropLabel">续房</h1>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">

                <div class="input-group mb-3">
                    <span class="input-group-text" id="inputGroup-sizing-default">天数</span>
                    <input v-model="newRoomDay" type="text" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default">
                </div>

            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                <button @click="roomRenewal" type="button" class="btn btn-primary">提交</button>
                <div v-if="errorMessage === 'success'" style="color: green;">{{errorMessage}}</div>
                <div v-else style="color: red;">{{errorMessage}}</div>
            </div>
            </div>
        </div>
        </div>


        <div></div>

        <!-- Button trigger modal -->
        <button @click="updateCurRoomId(info.roomId)" v-if="info.state === false" type="button" class="btn btn-info" data-bs-toggle="modal" data-bs-target="#staticBackdrop222" 
            style="width: 200px; margin-top: 15px;">
            换房
        </button>

        <!-- Modal -->
        <div class="modal fade" id="staticBackdrop222" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
            <div class="modal-header">
                <h1 class="modal-title fs-5" id="staticBackdropLabel">换房</h1>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">

                <div class="input-group mb-3">
                    <span class="input-group-text" id="inputGroup-sizing-default">新房ID</span>
                    <input v-model="newRoomId" type="text" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default">
                </div>
                <div class="input-group mb-3">
                    <span class="input-group-text" id="inputGroup-sizing-default">新房数量</span>
                    <input v-model="newRoomCount" type="text" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default">
                </div>
                <div class="input-group mb-3">
                    <span class="input-group-text" id="inputGroup-sizing-default">天数</span>
                    <input v-model="newRoomDay" type="text" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default">
                </div>
            
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                <button @click="roomChange" type="button" class="btn btn-primary">提交</button>
                <div v-if="errorMessage === 'success'" style="color: green;">{{errorMessage}}</div>
                <div v-else style="color: red;">{{errorMessage}}</div>
            </div>
            </div>
        </div>
        </div>

        <div></div>


        <!-- Button trigger modal -->
        <button @click="updateCurRoomId(info.roomId)" v-if="info.state === false" type="button" class="btn btn-danger" 
            data-bs-toggle="modal" data-bs-target="#staticBackdrop333" style="width: 200px; margin-top: 15px;">
            结账退房
        </button>

        <!-- Modal -->
        <div class="modal fade" id="staticBackdrop333" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
            <div class="modal-header">
                <h1 class="modal-title fs-5" id="staticBackdropLabel">结账退房</h1>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
            
                <div class="form-check">
                    <input v-model="weChatBtn" class="form-check-input" type="radio" name="flexRadioDefault" id="flexRadioDefault1">
                    <label class="form-check-label" for="flexRadioDefault1">
                        微信
                    </label>
                    </div>
                    <div class="form-check">
                    <input v-model="zhiFuBtn" class="form-check-input" type="radio" name="flexRadioDefault" id="flexRadioDefault2" checked>
                    <label class="form-check-label" for="flexRadioDefault2">
                        支付宝
                    </label>
                </div>  

            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                <button @click="roomPay" type="button" class="btn btn-primary">提交</button>
                <div v-if="errorMessage === 'success'" style="color: green;">{{errorMessage}}</div>
                <div v-else style="color: red;">{{errorMessage}}</div>
            </div>
            </div>
        </div>
        </div>

    </ContentBase>
</template>

<script>
import ContentBase from '@/components/ContentBase.vue';
import $ from "jquery";
import { useStore } from 'vuex';
import { ref } from 'vue';
import { Modal } from 'bootstrap/dist/js/bootstrap';

export default{
    name: "UserInfoView",
    components:{
        ContentBase,
    },

    /*

    超级大坑模态框内部接收不了vue的遍历属性！！！！！！！！！！！！！！！！！！

    */

    setup(){
        const store = useStore();
        let rooms = ref([]);
        let roomBookInfo = ref([]);
        let newRoomId = ref('');
        let newRoomDay = ref('');
        let newRoomCount = ref('');
        let errorMessage = ref('');
        let curRoomId = ref('');
        let weChatBtn = ref('');
        let zhiFuBtn = ref('');

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
            }
        });


        const updateCurRoomId = (roomId) =>{
            curRoomId.value = roomId;
        }

        const roomRenewal = () =>{
            errorMessage.value = '';
            if(newRoomDay.value === ''){
                errorMessage.value = "天数不能为空";
                return;
            }
            $.ajax({
                url: "http://127.0.0.1:5000/api/room/roomrenewal/",
                type: "post",
                data: {
                    roomId: curRoomId.value,
                    userId: store.state.user.id,
                    days: newRoomDay.value,
                },
                headers: {
                    'Authorization': "Bearer " + store.state.user.token,
                },
                success(resp) {
                    errorMessage.value = resp.error_message;
                    if(errorMessage.value === 'success'){
                        Modal.getInstance("#staticBackdrop111").hide();
                    }
                }
            });
        }

        const roomChange = () => {
            errorMessage.value = '';

            if(newRoomId.value === ''){
                errorMessage.value = "房间ID不能为空";
                return;
            }if(newRoomCount.value === ''){
                errorMessage.value = "房间数量不能为空";
                return;
            }if(newRoomDay.value === ''){
                errorMessage.value = "天数不能为空";
                return;
            }

            $.ajax({
                url: "http://127.0.0.1:5000/api/room/roomchange/",
                type: "post",
                data: {
                    roomId: curRoomId.value,
                    newRoomId: newRoomId.value,
                    userId: store.state.user.id,
                    count: newRoomCount.value,
                    days: newRoomDay.value,
                },
                headers: {
                    'Authorization': "Bearer " + store.state.user.token,
                },
                success(resp) {
                    errorMessage.value = resp.error_message;
                    Modal.getInstance("#staticBackdrop222").hide();
                }
            });
        }

        const roomPay = () => {
            errorMessage.value = '';
            
            if(zhiFuBtn.value !== 'on' && weChatBtn.value !== 'on'){
                errorMessage.value = '至少选择一个支付方式';
                return;
            }

            $.ajax({
                url: "http://127.0.0.1:5000/api/room/roompay/",
                type: "post",
                data: {
                    roomId: curRoomId.value,
                    userId: store.state.user.id,
                },
                headers: {
                    'Authorization': "Bearer " + store.state.user.token,
                },
                success(resp) {
                    errorMessage.value = resp.error_message;
                    Modal.getInstance("#staticBackdrop333").hide();
                }
            });
        }

        return{
            curRoomId,
            updateCurRoomId,
            rooms,
            roomBookInfo,
            roomChange,
            roomRenewal,
            newRoomId,
            newRoomDay,
            errorMessage,
            newRoomCount,
            roomPay,
            weChatBtn,
            zhiFuBtn,
        }
    }

}

</script>

<style scoped>
</style>