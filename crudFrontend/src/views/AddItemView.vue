<script setup lang="ts">
import { reactive } from 'vue';
import { ElMessage } from 'element-plus';

const form = reactive({
  name: '',
  description: '',
});

const submit = () => {
  fetch('http://localhost:8888/item/add', {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(form),
  }).then(res => res.json())
    .then(data => {
      if (data.success) {
        form.name = '';
        form.description = '';
        ElMessage({
          message: '添加成功',
          type: 'success',
        });
      } else {
        ElMessage.error('添加失败');
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
    </el-form-item>
  </el-form>
</template>
