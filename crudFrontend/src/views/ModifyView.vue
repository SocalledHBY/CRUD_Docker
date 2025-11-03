<script setup lang="ts">
import { reactive } from 'vue';
import { useRouter } from 'vue-router';
import { ElMessage } from 'element-plus';

const router = useRouter();

const form = reactive({
  itemId: router.currentRoute.value.params.itemId,
  name: '',
  description: '',
});
fetch(`${import.meta.env.VITE_API_URL}/item/${router.currentRoute.value.params.itemId}`)
  .then(res => res.json())
  .then(data => {
    if (data.success) {
      form.name = data.data.name;
      form.description = data.data.description;
    } else {
      ElMessage.error('获取物品信息失败');
      router.go(-1);
    }
  });

const submit = () => {
  fetch(`${import.meta.env.VITE_API_URL}/item/modify`, {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(form),
  }).then(res => res.json())
    .then(data => {
      if (data.success) {
        ElMessage({
          message: '修改成功',
          type: 'success',
        });
        router.go(-1);
      } else {
        ElMessage.error('修改失败');
      }
    });
};
</script>

<template>
  <el-form :model="form" label-width="auto" style="max-width: 600px">
    <el-form-item label="物品名称">
      <el-input v-model="form.name" />
    </el-form-item>
    <el-form-item label="物品描述">
      <el-input v-model="form.description" type="textarea" />
    </el-form-item>
    <el-form-item>
      <el-button type="primary" @click="submit">提交</el-button>
      <el-button @click="router.go(-1)">返回</el-button>
    </el-form-item>
  </el-form>
</template>
