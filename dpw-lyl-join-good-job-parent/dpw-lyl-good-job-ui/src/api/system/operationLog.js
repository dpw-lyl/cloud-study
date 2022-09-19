import request from '@/utils/request'

// 查询操作日志列表
export function list(query) {
  return request({
    url: '/system/sysOperationLog/list',
    method: 'get',
    params: query
  })
}

// 删除操作日志
export function delOperationLog(operId) {
  return request({
    url: '/system/sysOperationLog/' + operId,
    method: 'delete'
  })
}

// 清空操作日志
export function cleanOperationLog() {
  return request({
    url: '/system/sysOperationLog/clean',
    method: 'delete'
  })
}
