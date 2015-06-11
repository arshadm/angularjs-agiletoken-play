package services

import javax.xml.bind.annotation.XmlRootElement

import models.converter.{QueueItemEntityConverter, QueueEntityConverter}
import models.dao.{QueueItemDao, QueueDao}
import models.schema.{QueueItem, Queue}
import play.api.mvc._
import play.db.jpa.Transactional
import scala.collection.JavaConverters._

import scala.beans.BeanProperty

@XmlRootElement(name = "ServiceResponse")
class ServiceResponse(@BeanProperty var status: String = "SUCCESS", @BeanProperty var details: String = "")
{
}

@XmlRootElement(name = "FetchQueueResponse")
class FetchQueuesResponse(@BeanProperty var queues : java.util.List[Queue]) extends ServiceResponse
{
}

@XmlRootElement(name = "NewQueuesResponse")
class NewQueueResponse(@BeanProperty var queue : Queue) extends ServiceResponse
{
}

@XmlRootElement(name = "SaveQueueRequest")
class SaveQueueRequest(@BeanProperty var queue: Queue)
{
  def this() = this(null)
}

@XmlRootElement(name = "SaveQueueResponse")
class SaveQueueResponse extends ServiceResponse
{
}

@XmlRootElement(name = "GetQueueResponse")
class GetQueueResponse(@BeanProperty var queue: Queue) extends ServiceResponse
{
}

@XmlRootElement(name = "NewQueueItemResponse")
class NewQueueItemResponse(@BeanProperty var queueItem: QueueItem) extends ServiceResponse
{
}

@XmlRootElement(name = "GetQueueItemResponse")
class GetQueueItemResponse(@BeanProperty var queueItem: QueueItem) extends ServiceResponse
{
}

@XmlRootElement(name = "SaveQueueItemRequest")
class SaveQueueItemRequest(@BeanProperty var queueItem: QueueItem)
{
  def this() = this(null)
}

@XmlRootElement(name = "SaveQueueItemResponse")
class SaveQueueItemResponse extends ServiceResponse
{
}

class QueueService extends Controller {
  val queueDao = new QueueDao
  val queueItemDao = new QueueItemDao
  val queueItemEntityConverter = new QueueItemEntityConverter(queueDao, queueItemDao)
  val queueEntityConverter = new QueueEntityConverter(queueDao, queueItemEntityConverter)

  @Transactional
  def fetchQueues() = Action {
    val queues = queueDao.findAll()

    Ok(new FetchQueuesResponse(queues.map(q => queueEntityConverter.convert(q)).asJava))
  }

  def newQueue() = Action {
    val queue = new Queue

    Ok(new NewQueueResponse(queue))
  }

  @Transactional
  def getQueue(queueId: java.lang.Long) = Action {
    val queue = queueEntityConverter.convert(queueDao.findById(queueId), true)

    Ok(new GetQueueResponse(queue))
  }

  @Transactional
  def saveQueue(saveQueueRequest: SaveQueueRequest) = Action {
    val queueEntity = queueEntityConverter.convertBack(saveQueueRequest.queue)

    queueDao.save(queueEntity)

    Ok(new SaveQueueResponse)
  }

  @Transactional
  def deleteQueue(queueId: java.lang.Long) = Action {
    queueDao.removeById(queueId)

    Ok(new ServiceResponse())
  }

  def newQueueItem(queueId: java.lang.Long) = Action {
    val queueItem = new QueueItem

    queueItem.queueId = queueId
    queueItem.mergedInd = false

    Ok(new NewQueueItemResponse(queueItem))
  }

  @Transactional
  def getQueueItem(queueItemId: java.lang.Long) = Action {
    val queueItem = queueItemEntityConverter.convert(queueItemDao.findById(queueItemId))

    Ok(new GetQueueItemResponse(queueItem))
  }

  @Transactional
  def markItemMerged(queueItemId: java.lang.Long) = Action {
    val queueItem = queueItemDao.findById(queueItemId)
    queueItem.mergedInd = true

    queueItemDao.save(queueItem)

    Ok(new ServiceResponse())
  }

  @Transactional
  def saveQueueItem(saveQueueItemRequest: SaveQueueItemRequest) = Action {
    val queueItemEntity = queueItemEntityConverter.convertBack(saveQueueItemRequest.queueItem)

    queueItemDao.save(queueItemEntity)

    Ok(new SaveQueueItemResponse)
  }

  @Transactional
  def deleteQueueItem(queueItemId: java.lang.Long) =  Action {
    queueItemDao.removeById(queueItemId)

    Ok(new ServiceResponse())
  }
}
